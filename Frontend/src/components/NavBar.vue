.<template>
   <div class="nav">
      <div class="nav-left">
        <div class="logo">
        </div>
      </div>

    <div class="nav-center">
      <div class="search-container">
        <input type="text" placeholder="Search">
        <div class="search-button">
        </div>
      </div>
    </div>

    <div class="nav-right">
      <p><router-link :to="{name:'Ranking'}">Ranking</router-link></p>
      <p><router-link :to="{name:'Bucket'}">Bucket-list</router-link></p>
      <p><router-link :to="{name:'Challenge'}">Challenge</router-link></p>
      <p><router-link :to="{name:'myPage'}">myPage</router-link></p>
      <p><router-link :to="{name:'Join'}">Join</router-link></p>
      <p><router-link :to="{name:'Login'}">login</router-link></p>
      <p>log-out</p>
    </div>
  </div>
</template>

<script>
export default {
    
}
</script>

<style>

</style>