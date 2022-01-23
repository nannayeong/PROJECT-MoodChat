<template>
  <div id="sendMsg">
    <img class="profile"
      :src="getMsgGroup.user.profile.path"
      :alt="getMsgGroup.user.profile.name"
    >
    <div class="body"> 
      <span class="nickname"> {{ getMsgGroup.user.nickname }} </span>
      <span class="msg"
        v-for="(msg, value) in getMsgGroup.msgList"
        :key="value"
      > {{ msg }} </span>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    msgGroup: String,
  },
  computed: {
    getMsgGroup() {

      return this.msgGroup
    }
  },
  data() {
    return {
    }
  }
}
</script>