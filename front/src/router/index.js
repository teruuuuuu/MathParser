import Vue from 'vue'
import Router from 'vue-router'
import EditorApp from '@/components/myEditor/Main'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'EditorApp',
      component: EditorApp
    }
  ]
})
