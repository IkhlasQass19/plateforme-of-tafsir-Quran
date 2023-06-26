import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import QuranView from '../views/QuranView.vue'
import SearchView from '../views/Principe.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'search',
      component: SearchView
    },
    {
      path: '/quran/:id?',
      name: 'quran',
      component:  QuranView
    },
    {
      path: '/main',
      name: 'home',
      component:  HomeView
    }
  ]
})

export default router
