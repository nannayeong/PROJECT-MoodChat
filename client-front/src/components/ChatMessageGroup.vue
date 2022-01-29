<template>
  <div id="chatMessageGroup" :class="data.type">
    <img class="profileImg"
      :src="require(`../assets${data.profileImgPath}`)" alt="profileImg"/>
    <div class="chatMessageGroupBody">
      <h5 class="nickname">{{ data.nickname }}</h5>
      <ol>
        <li
          v-for="(message, index) in data.messageList"
          :key="`message-${index}`"
        >
          <p class="message">{{ message }}</p>
          <span class="date"
            v-if="index === data.messageList.length - 1"
          >{{ date }}</span>
        </li>
      </ol>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    data: {
      type: Object,
      default: () => {
        return {
          type: '',
          profileImgPath: '',
          nickname: '',
          messageList: [],
          date: ''
        }
      }
    }
  },
  data() {
    return {
    }
  },
  computed: {
    date() {
      let date = new Date(this.data.date)

      let hours = 
        date.getHours() < 12 ? `오전 ${date.getHours()}` : `오후 ${date.getHours() - 12}`
      let minutes = String(date.getMinutes()).padStart(2, 0)

      return hours + ':' + minutes
    }
  },
  methods: {
  }
}
</script>