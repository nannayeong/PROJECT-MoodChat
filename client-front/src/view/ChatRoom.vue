<template>
  <div id="chatRoom">
    <header>
      <button>이전</button>
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
      <div class="sendMessageContainer">
        <button class="moodPaletteBtn"
          @click="moodPaletteSwitch"
        >
          <div :class="moodColor"></div>
        </button>
        <input type="text">
        <button class="sendMessageBtn"
          v-if="sendable"
        >send</button>
        <button class="option"
          v-else
        >op</button>
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
  mounted() {
    this.scrollChatRoomBody(this.$refs.messageGroupContainer.clientHeight)
  },
  watch: {
    moodPaletteOn() {

      let targetScrollTop = 0
      
      if (this.moodPaletteOn) {

        this.optionContainerHeight = 150
        this.prevScrollTop = this.$refs.chatRoomBody.scrollTop
        targetScrollTop = this.prevScrollTop + this.optionContainerHeight
      } else {

        targetScrollTop = this.prevScrollTop
        this.optionContainerHeight = 0
      }

      this.$nextTick(() => {

        this.scrollChatRoomBody(targetScrollTop)
      })
    }
  },
  data() {
    return {
      moodPaletteOn: false,
      moodColor: 'red',
      sendMsg: '',
      optionContainerHeights: {
        moodPalette: 150
      },
      optionContainerHeight: 0,
      prevScrollTop: 0,
    }
  },
  computed: {
    optionContainerOn() {

      return this.moodPaletteOn
    },
    optionContainerHeightPx() {

      return this.optionContainerHeight + 'px'
    },
    messageGroupList() {

      let messageGroupList = [
        {
          type: 'receive',
          profileImgPath: '/logo.png',
          nickname: '챗봇이름입니다.챗봇이름입니다.챗봇이름입니다.챗봇이름입니다.챗봇이름입니다.챗봇이름입니다.챗봇이름입니다.챗봇이름입니다.챗봇이름입니다.챗봇이름입니다.',
          date: Date.now(),
          messageList: [
            'message1message1message1message1message1message1message1', 'message2message2message2message2message2message2message2'
          ]
        },
        {
          type: 'receive',
          profileImgPath: '/logo.png',
          nickname: '챗봇이름입니다.챗봇이름입니다.챗봇이름입니다.챗봇이름입니다.챗봇이름입니다.챗봇이름입니다.챗봇이름입니다.챗봇이름입니다.챗봇이름입니다.챗봇이름입니다.',
          date: Date.now(),
          messageList: [
            'message1message1message1message1message1message1message1', 'message2message2message2message2message2message2message2'
          ]
        },
        {
          type: 'send',
          profileImgPath: '/logo.png',
          nickname: '유저이름입니다.',
          date: Date.now(),
          messageList: [
            'message1', 'message2'
          ]
        },
        {
          type: 'send',
          profileImgPath: '/logo.png',
          nickname: '유저이름입니다.',
          date: Date.now(),
          messageList: [
            'message1', 'message2'
          ]
        },
        {
          type: 'send',
          profileImgPath: '/logo.png',
          nickname: '유저이름입니다.',
          date: Date.now(),
          messageList: [
            'message1', 'message2'
          ]
        },
        {
          type: 'send',
          profileImgPath: '/logo.png',
          nickname: '유저이름입니다.',
          date: Date.now(),
          messageList: [
            'message1', 'message2'
          ]
        },
        {
          type: 'send',
          profileImgPath: '/logo.png',
          nickname: '유저이름입니다.',
          date: Date.now(),
          messageList: [
            'message1', 'message2'
          ]
        },
      ]
      return messageGroupList
    },
    sendable() {

      return this.sendMsg !== ''
    }
  },
  methods: {
    scrollChatRoomBody(targetY) {

      this.$refs.chatRoomBody.scrollTo(0, targetY)
    },
    moodPaletteSwitch() {

      this.moodPaletteOn = !this.moodPaletteOn
    }
  }
}
</script>