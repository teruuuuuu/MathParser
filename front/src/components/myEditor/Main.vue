<template>
  <div id="EditorApp">

    <div id="header">
      <md-toolbar>
        <h1 class="md-title">数式パーサー</h1>
        <md-button class="md-raised execButton" v-on:click.native="exec(event)" >実行</md-button>
      </md-toolbar>
    </div>

    <md-input-container class="myEdit">
      <label>入力</label>
      <md-textarea rows="5" v-model="input"></md-textarea>
    </md-input-container>

    <md-input-container class="myEdit">
      <label>出力</label>
      <md-textarea rows="5" v-model="output"></md-textarea>
    </md-input-container>


  </div>
</template>

<script>
var URL = process.env.REQUEST_URL
export default {
  components: {},
  data: function () {
    return {
      input: '',
      output: ''
    }
  },
  methods: {
    exec: function (event) {
      var formData = new FormData()
      formData.append('input', this.input)
      this.$http.post(URL.PARSE_URL, formData).then(response => {
        this.output = response.body.parse
      }, response => {
      })
    }
  },
  computed: {
  },
  mounted: function () {
  }
}

</script>

<style scoped>
p {
  font-size: 2em;
  text-align: center;
}

.myEdit {
  width: 95%;
  margin: 0px 0px 0px 15px;
  font-family: MS PGothic, Osaka, Arial, sans-serif;
}

.md-raised {
      margin-left: 30px;
}

.execButton{
  right: 30px;
  margin-left: auto;
}

</style>
