<template>
  <div class="img-container">
    <div class="logo">
      <div class="header-container">
        <div class="content">
          <h2>DREAMY</h2>
          <h2>DREAMY</h2>
        </div>
      </div>
    </div>
    <router-link to="/user/join">
      <div id=container>
      make 
      <div id=flip>
        <div><div>friends</div></div>
        <div><div>bucket list</div></div>
        <div><div>challenge</div></div>
      </div>
      now !
      </div>
    </router-link>
  <p>|| DREAMY ||</p>
  </div>
</template>

<script>
export default {

}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Source+Serif+Pro:wght@200;300;400;600;700;900&display=swap');
.content {
  color: transparent;
  position: relative;
  font-family: 'Source Serif Pro', serif;
}
.content h2 {

  position: absolute;
  transform: translate(-50%, -50%);
  font-size: 10em;
}
.content h2:nth-child(1) {
  color: transparent;
  /* 배경 색 */
  -webkit-text-stroke: 2px #000000;
}
.content h2:nth-child(2) {
  /* 채울 색 */
  color: #000000;
  animation: animate 4s ease-in-out infinite;
}
@keyframes animate {
  0%, 100% {
    clip-path: polygon(0 50%, 9% 45%, 18% 43%, 29% 42%, 40% 46%, 48% 52%, 56% 58%, 67% 59%, 76% 57%, 87% 53%, 100% 51%, 100% 100%, 0% 100%);
  }
  50% {
    clip-path: polygon(0 49%, 9% 50%, 16% 52%, 23% 54%, 33% 58%, 43% 56%, 54% 50%, 64% 41%, 78% 37%, 90% 39%, 100% 43%, 100% 100%, 0% 100%);
  }
}
.logo {
  position: fixed;
  top: 25%;
  left: 50%;
  min-height: 100vh;
}
@import url('https://fonts.googleapis.com/css?family=Roboto:700');

#container {
  color:rgb(241, 245, 255);
  text-transform: uppercase;
  /* 글씨 크기 인데 배경 색이 못따라온다? */
  font-size: 32px; 
  font-weight:bold;
  padding-top:200px;  
  position:fixed;
  width:100%;
  bottom:35%;
  display:block;
  z-index: 1;
  opacity: 1;
}

#flip {
  height: 55px;
  overflow:hidden;
}

#flip > div > div {
  color:#fff;
  padding:1px 12px;
  height:45px;
  margin-bottom:45px;
  display:inline-block;
}

#flip div:first-child {
  animation: show 10s linear infinite;
}

#flip div div {
  background:#42c58a;
}
#flip div:first-child div {
  background:#4ec7f3;
}
#flip div:last-child div {
  background:#DC143C;
}

@keyframes show {
  0% {margin-top:-270px;}
  5% {margin-top:-180px;}
  33% {margin-top:-180px;}
  38% {margin-top:-90px;}
  66% {margin-top:-90px;}
  71% {margin-top:0px;}
  99.99% {margin-top:0px;}
  100% {margin-top:-270px;}
}

p {
  position:fixed;
  width:100%;
  bottom:150px;
  font-size:1.5rm;
  color:rgb(255, 255, 255);
  margin-top:200px;

}
</style>

