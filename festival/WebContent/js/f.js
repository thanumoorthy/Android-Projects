$(function(){var t=function(){var t=this,e=function(t,e){return~~(Math.random()*(e-t+1)+t)},a=function(t,e,a,r,n,o,i,c){return!(t+a<n||n+i<t||e+r<o||o+c<e)};window.requestAnimFrame=function(){return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||window.oRequestAnimationFrame||window.msRequestAnimationFrame||function(t){window.setTimeout(t,1e3/60)}}(),t.init=function(){t.canvas=document.createElement("canvas"),t.canvas.width=t.cw=$(window).innerWidth(),t.canvas.height=t.ch=$(window).innerHeight()-20,t.canvas.setAttribute("onclick","playSound()"),t.particles=[],t.partCount=150,t.fireworks=[],t.mx=t.cw/2,t.my=t.ch/2,t.currentHue=30,t.partSpeed=5,t.partSpeedVariance=10,t.partWind=50,t.partFriction=5,t.partGravity=1,t.hueMin=0,t.hueMax=360,t.fworkSpeed=4,t.fworkAccel=10,t.hueVariance=30,t.flickerDensity=25,t.showShockwave=!0,t.showTarget=!1,t.clearAlpha=25,$(document.body).append(t.canvas),t.ctx=t.canvas.getContext("2d"),t.ctx.lineCap="round",t.ctx.lineJoin="round",t.lineWidth=1,t.bindEvents(),t.canvasLoop(),t.canvas.onselectstart=function(){return!1}},t.createParticles=function(a,r,n){for(var o=t.partCount;o--;){var i={x:a,y:r,coordLast:[{x:a,y:r},{x:a,y:r},{x:a,y:r}],angle:e(0,360),speed:e(t.partSpeed-t.partSpeedVariance<=0?1:t.partSpeed-t.partSpeedVariance,t.partSpeed+t.partSpeedVariance),friction:1-t.partFriction/100,gravity:t.partGravity/2,hue:e(n-t.hueVariance,n+t.hueVariance),brightness:e(50,80),alpha:e(40,100)/100,decay:e(10,50)/1e3,wind:(e(0,t.partWind)-t.partWind/2)/25,lineWidth:t.lineWidth};t.particles.push(i)}},t.updateParticles=function(){for(var e=t.particles.length;e--;){var r=t.particles[e],n=r.angle*Math.PI/180,o=Math.cos(n)*r.speed,i=Math.sin(n)*r.speed;r.speed*=r.friction,r.coordLast[2].x=r.coordLast[1].x,r.coordLast[2].y=r.coordLast[1].y,r.coordLast[1].x=r.coordLast[0].x,r.coordLast[1].y=r.coordLast[0].y,r.coordLast[0].x=r.x,r.coordLast[0].y=r.y,r.x+=o,r.y+=i,r.y+=r.gravity,r.angle+=r.wind,r.alpha-=r.decay,(!a(0,0,t.cw,t.ch,r.x-r.radius,r.y-r.radius,2*r.radius,2*r.radius)||r.alpha<.05)&&t.particles.splice(e,1)}},t.drawParticles=function(){for(var a=t.particles.length;a--;){var r=t.particles[a],n=e(1,3)-1;if(t.ctx.beginPath(),t.ctx.moveTo(Math.round(r.coordLast[n].x),Math.round(r.coordLast[n].y)),t.ctx.lineTo(Math.round(r.x),Math.round(r.y)),t.ctx.closePath(),t.ctx.strokeStyle="hsla("+r.hue+", 100%, "+r.brightness+"%, "+r.alpha+")",t.ctx.stroke(),t.flickerDensity>0){var o=50-t.flickerDensity;if(e(0,o)===o){t.ctx.beginPath(),t.ctx.arc(Math.round(r.x),Math.round(r.y),e(r.lineWidth,r.lineWidth+3)/2,0,2*Math.PI,!1),t.ctx.closePath();var i=e(50,100)/100;t.ctx.fillStyle="hsla("+r.hue+", 100%, "+r.brightness+"%, "+i+")",t.ctx.fill()}}}},t.createFireworks=function(a,r,n,o){var i={x:a,y:r,startX:a,startY:r,hitX:!1,hitY:!1,coordLast:[{x:a,y:r},{x:a,y:r},{x:a,y:r}],targetX:n,targetY:o,speed:t.fworkSpeed,angle:Math.atan2(o-r,n-a),shockwaveAngle:Math.atan2(o-r,n-a)+Math.PI/180*90,acceleration:t.fworkAccel/100,hue:t.currentHue,brightness:e(50,80),alpha:e(50,100)/100,lineWidth:t.lineWidth};t.fireworks.push(i)},t.updateFireworks=function(){for(var e=t.fireworks.length;e--;){var a=t.fireworks[e];t.ctx.lineWidth=a.lineWidth,vx=Math.cos(a.angle)*a.speed,vy=Math.sin(a.angle)*a.speed,a.speed*=1+a.acceleration,a.coordLast[2].x=a.coordLast[1].x,a.coordLast[2].y=a.coordLast[1].y,a.coordLast[1].x=a.coordLast[0].x,a.coordLast[1].y=a.coordLast[0].y,a.coordLast[0].x=a.x,a.coordLast[0].y=a.y,a.startX>=a.targetX?a.x+vx<=a.targetX?(a.x=a.targetX,a.hitX=!0):a.x+=vx:a.x+vx>=a.targetX?(a.x=a.targetX,a.hitX=!0):a.x+=vx,a.startY>=a.targetY?a.y+vy<=a.targetY?(a.y=a.targetY,a.hitY=!0):a.y+=vy:a.y+vy>=a.targetY?(a.y=a.targetY,a.hitY=!0):a.y+=vy,a.hitX&&a.hitY&&(t.createParticles(a.targetX,a.targetY,a.hue),t.fireworks.splice(e,1))}},t.drawFireworks=function(){var a=t.fireworks.length;for(t.ctx.globalCompositeOperation="lighter";a--;){var r=t.fireworks[a];t.ctx.lineWidth=r.lineWidth;var n=e(1,3)-1;t.ctx.beginPath(),t.ctx.moveTo(Math.round(r.coordLast[n].x),Math.round(r.coordLast[n].y)),t.ctx.lineTo(Math.round(r.x),Math.round(r.y)),t.ctx.closePath(),t.ctx.strokeStyle="hsla("+r.hue+", 100%, "+r.brightness+"%, "+r.alpha+")",t.ctx.stroke(),t.showTarget&&(t.ctx.save(),t.ctx.beginPath(),t.ctx.arc(Math.round(r.targetX),Math.round(r.targetY),e(1,8),0,2*Math.PI,!1),t.ctx.closePath(),t.ctx.lineWidth=1,t.ctx.stroke(),t.ctx.restore()),t.showShockwave&&(t.ctx.save(),t.ctx.translate(Math.round(r.x),Math.round(r.y)),t.ctx.rotate(r.shockwaveAngle),t.ctx.beginPath(),t.ctx.arc(0,0,r.speed/5*1,0,Math.PI,!0),t.ctx.strokeStyle="hsla("+r.hue+", 100%, "+r.brightness+"%, "+e(25,60)/100+")",t.ctx.lineWidth=r.lineWidth,t.ctx.stroke(),t.ctx.restore())}},t.bindEvents=function(){$(window).on("resize",function(){clearTimeout(t.timeout),t.timeout=setTimeout(function(){t.canvas.width=t.cw=$(window).innerWidth(),t.canvas.height=t.ch=$(window).innerHeight(),t.ctx.lineCap="round",t.ctx.lineJoin="round"},100)}),$(t.canvas).on("mousedown",function(a){t.mx=a.pageX-t.canvas.offsetLeft,t.my=a.pageY-t.canvas.offsetTop,t.currentHue=e(t.hueMin,t.hueMax),t.createFireworks(t.cw/2,t.ch,t.mx,t.my),$(t.canvas).on("mousemove.fireworks",function(a){t.mx=a.pageX-t.canvas.offsetLeft,t.my=a.pageY-t.canvas.offsetTop,t.currentHue=e(t.hueMin,t.hueMax),t.createFireworks(t.cw/2,t.ch,t.mx,t.my)})}),$(t.canvas).on("mouseup",function(e){$(t.canvas).off("mousemove.fireworks")})},t.clear=function(){t.particles=[],t.fireworks=[],t.ctx.clearRect(0,0,t.cw,t.ch)},t.canvasLoop=function(){requestAnimFrame(t.canvasLoop,t.canvas),t.ctx.globalCompositeOperation="destination-out",t.ctx.fillStyle="rgba(0,0,0,"+t.clearAlpha/100+")",t.ctx.fillRect(0,0,t.cw,t.ch),t.updateFireworks(),t.updateParticles(),t.drawFireworks(),t.drawParticles()},t.init()};new t});