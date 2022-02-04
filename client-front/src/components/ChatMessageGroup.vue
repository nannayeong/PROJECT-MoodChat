<template>
  <div id="chatMessageGroup" :class="data.type">
    <img class="profileImg"
      :src="require(`../assets/img/${data.profileImgPath}`)" alt="profileImg"/>
    <div class="chatMessageGroupBody">
      <h5 class="nickname"
      >{{ data.nickname }}</h5>
      <ol>
        <li
          v-for="(message, index) in data.messageList"
          :key="`message-${index}`"
        >
          <p class="message">{{ message }}</p>
          <span class="date"
            v-if="index === data.messageList.length - 1"
          >{{ data.date }}</span>
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

  },
  methods: {
  }
}
</script>