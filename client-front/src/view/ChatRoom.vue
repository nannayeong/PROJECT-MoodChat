<template>
  <div id="chatRoom">
    <header>
      <button class="movePrev">
        <img src="../assets/img/movePrev.png" alt="movePrev">
      </button>
      <h2>챗봇</h2>
    </header>
    <div class="chatRoomBody"
      ref="chatRoomBody"
    >
      <div class="messageGroupContainer"
        ref="messageGroupContainer"
        :style="{'padding-bottom': optionContainerHeightPx}"
      >
        <ChatMessageGroup
          v-for="(messageGroup, messageGroupIndex) in messageGroupList"
          :key="`messageGroup-${messageGroupIndex}`"
          :data="messageGroup"
        />
      </div>
    </div>
    <footer>
      <div class="optionContainer"
        v-if="optionContainerOn"
        ref="optionContainer"
        :style="{'max-height': optionContainerHeightPx}"
      >
        <ChatMoodPalette
          v-if="moodPaletteOn"
          :style="{'min-height': optionContainerHeightPx}"
        />
      </div>
      <div class="sendMessageContainer"
        :style="{'height': sendMsgContainerHeightPx}"
      >
        <button class="moodPaletteBtn"
          @click="moodPaletteSwitch"
        >
          <div :class="moodColor"></div>
        </button>
        <textarea 
          class="sendMessageInput"
          cols="10" rows="3"
          v-model="sendMsg"
        ></textarea>
        <button class="sendMessageBtn"
          v-if="sendable"
          @click="saveSendMsg"
        >send</button>
        <button class="hashTagBtn"
          v-else
        >
          <img src="../assets/img/hashTag.png" alt="hashTag">
        </button>
      </div>
    </footer>
  </div>
</template>

<script>
import ChatMessageGroup from '../components/ChatMessageGroup'
import ChatMoodPalette from '../components/ChatMoodPalette.vue'
export default {
  components: {
    ChatMessageGroup,
    ChatMoodPalette
  },
  data() {
    return {
      moodPaletteOn: false,
      moodColor: 'red',
      messageGroupList: [],
      sendMsg: '',
      sendMsgContainerHeight: 40,
      optionContainerHeights: {
        moodPalette: 150
      },
      optionContainerHeight: 0,
      prevScrollTop: 0,
    }
  },
  computed: {
    sendMsgContainerHeightPx() {

      return this.sendMsgContainerHeight + 'px'
    },
    optionContainerOn() {

      return this.moodPaletteOn
    },
    optionContainerHeightPx() {

      return this.optionContainerHeight + 'px'
    },
    sendable() {

      return this.sendMsg !== ''
    }
  },
  mounted() {
    this.loadMessageGroupList()
    this.scrollChatRoomBody(this.$refs.messageGroupContainer.clientHeight)
  },
  watch: {
    moodPaletteOn() {

      let targetScrollTop = 0
      
      if (this.moodPaletteOn) {

        this.optionContainerHeight = this.optionContainerHeights['moodPalette']
        targetScrollTop = this.$refs.chatRoomBody.scrollTop + this.optionContainerHeight
      } else {

        targetScrollTop = this.$refs.chatRoomBody.scrollTop - this.optionContainerHeight
        this.optionContainerHeight = 0
      }

      this.$nextTick(() => {

        this.scrollChatRoomBody(targetScrollTop)
      })
    },
    sendMsg() {

      this.sendMessageInputHeightResize()
    }
  },
  methods: {
    loadMessageGroupList() {

      this.messageGroupList = [
        
      ]
    },
    messageGroup(type, profileImgPath, nickname, date, messageList) {

      return {
        type: type,
        profileImgPath: profileImgPath,
        nickname: nickname,
        date: date,
        messageList: messageList
      }
    },
    dateFormat(paramDate) {
      let date = new Date(paramDate)

      let hours = 
        date.getHours() < 12 ? `오전 ${date.getHours()}` : `오후 ${date.getHours() - 12}`
      let minutes = String(date.getMinutes()).padStart(2, 0)

      return hours + ':' + minutes
    },
    scrollChatRoomBody(targetY) {

      this.$refs.chatRoomBody.scrollTo(0, targetY)
    },
    moodPaletteSwitch() {

      this.moodPaletteOn = !this.moodPaletteOn
    },
    sendMessageInputHeightResize() {

      let sendMsgPCount = parseInt(this.sendMsg.split('\n').length)

      if (sendMsgPCount <= 3) {

        this.sendMsgContainerHeight = 40 + (20 * (sendMsgPCount - 1))
      } else {

        this.sendMsgContainerHeight = 40 + (20 * 2)
      } 
    },
    saveSendMsg() {

      let sendMsgDate = this.dateFormat(Date.now())
      let messageGroup = this.messageGroupList.find(mgl => mgl.type === 'send' && mgl.date === sendMsgDate)

      if (typeof messageGroup === 'undefined' || messageGroup == null) {

        messageGroup = this.messageGroup('send', 'defaultProfile.png', '', sendMsgDate, [this.sendMsg])
        this.messageGroupList.push(messageGroup)
      } else {

        messageGroup.messageList.push(this.sendMsg)
      }
    }
  }
}
</script>