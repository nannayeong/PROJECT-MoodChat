<template>
  <div id="chatRoom"
    :style="colorProps.chatRoom"
  >
    <div class="header">
      <button
        @click="close"
      >닫기</button>
      <div class="msgSearchContainer"
        v-if="msgSearchContainerOn"
      >
        <input type="text"
          placeholder="대화 내용 검색"
          @keyup:enter="searchMsg"
        >
      </div>
      <button class="msg-search-btn"
        v-if="!msgSearchContainerOn"
        @click="msgSearchContainerSwitch"
      >search</button>
    </div>
    <div class="body">
      <div :class="`msg-group ${msgGroup.type === 'sendMsg' ? 'send-msg' : 'receive-msg'}`"
        v-for="(msgGroup, index) in msgGroupList"
        :key="`msg-${index}`"
      >
        <img class="profile"
          v-if="msgGroup.type === 'receiveMsg'"
          :src="msgGroup.user.profile.path"
          :alt="msgGroup.user.profile.name"
        >
        <div class="msg-wrapper-wrapper"> 
          <p class="nickname"
            v-if="msgGroup.type === 'receiveMsg'"
          > {{ msgGroup.user.nickname }} 
          </p>
          <p class="msg-wrapper"
            v-for="(msg, index) in msgGroup.msgList"
            :key="`msg-${index}`"
          > 
            <span class="msg"
              v-html="changeMsgToHTMLFormat(msg.msg)"
            ></span>
            <span class="date"
              v-if="index === msgGroup.msgList.length - 1"
            > {{ msgGroup.date }} </span>
          </p>
        </div>
      </div>
    </div>
    <div class="footer">
      <div class="msg-emotion-container"
        v-if="msgEmotionContainerOn"
      >
        <button
          v-for="(msgEmotion, index) in msgEmotionList"
          :key="`msgEmotion-${index}`"
          :style="msgEmotion.color"
          @click="selectMsgEmotion(msgEmotion)"
        > {{ msgEmotion.title }} </button>
      </div> 
      <div class="msg-sender">
        <button class="msg-emotion-btn"
          @click="msgEmotionContainerSwitch"
          :style="msgToSend.msgEmotion.color"
        > E </button>    
        <textarea
          ref="msgTextarea"
          @keyup="textareaResize"
          v-model="msgToSend.msg"
          :style="msgToSend.msgTextareaHeight"
        ></textarea>
        <button type="button"
          v-if="msgToSend.msg === ''"
        >#</button>
        <button type="submit"
          v-else
          :class="msgToSend.msg !== '' ? 'active' : 'inactive'"
          @click="sendMsg"
        >전송</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {
    
  },
  created() {
    this.loadMsgEmotion()
    this.loadMsgList()
  },
  data() {
    return {
      msgEmotionContainerOn: false,
      msgSearchContainerOn: false,
      msgEmotionList: [],
      msgGroupList: [],
      colorProps: {
        chatRoom: {
          'background-color': 'white'
        }
      },
      msgToSend: {
        msgTextareaHeight: {},
        msgEmotion: {},
        msg: '',
      },
    }
  },
  computed: {
  },
  methods: {
    loadMsgEmotion() {

      let msgEmotionDefaultTitle = 'default'

      this.msgEmotionList = [
        {
          title: 'default',
          color: {
            'background-color': 'gray'
          }
        },
        {
          title: 'angry',
          color: {
            'background-color': 'red'
          }
        },
        {
          title: 'happy',
          color: {
            'background-color': 'green'
          }
        },
      ]

      this.msgToSend.msgEmotion = this.msgEmotionList.find(msgEmotion => msgEmotion.title === msgEmotionDefaultTitle)
    },
    loadMsgList() {

      this.msgGroupList = [
        {
          type: 'sendMsg',
          user: {
            nickname: 'nanna',
            profile: {
              path: require('../assets/logo.png'),
              name: ''
            }
          },
          msgList: [
            {
              msg: 'msg1',
              msgEmotion: 'default'
            },
            {
              msg: 'msg2dddd',
              msgEmotion: 'default'
            },
          ],
          date: '2022.01'
        },
        {
          type: 'receiveMsg',
          user: {
            nickname: 'user',
            profile: {
              path: require('../assets/logo.png'),
              name: ''
            }
          },
          msgList: [
            {
              msg: 'msg3d',
              msgEmotion: 'default'
            },
          ],
          date: '2022.01'
        }
      ]
    },
    close() {

      if (this.msgSearchContainerOn) {

        this.msgSearchContainerSwitch()
        return
      }
    },
    msgEmotionContainerSwitch() {
      
      this.msgEmotionContainerOn = !this.msgEmotionContainerOn
    },
    msgSearchContainerSwitch() {
      
      this.msgSearchContainerOn = !this.msgSearchContainerOn
    },
    searchMsg() {


    },
    selectMsgEmotion(msgEmotion) {

      this.msgToSend.msgEmotion = msgEmotion
      this.msgEmotionContainerSwitch()
    },
    textareaResize() {

      let msgTextareaScrollHeight = this.$refs.msgTextarea.scrollHeight
      let resultMTSH = msgTextareaScrollHeight > 100 ? 100 : msgTextareaScrollHeight + 10

      this.msgToSend.msgTextareaHeight = {
        'height': resultMTSH + 'px'
      }
    },
    sendMsg() {

      let pastSendMsgGroupEqDate = 
        this.msgGroupList.find(msgGroup => msgGroup.type === 'sendMsg' && msgGroup.date === '2022.03')

        let msgToSend = {
          msg: this.msgToSend.msg,
          msgEmotion: this.msgToSend.msgEmotion
        }
      
      if (!pastSendMsgGroupEqDate) {

        this.msgGroupList.push({
          type: 'sendMsg',
          msgList: [msgToSend],
          date: '2022.03'
        })
      } else {

        pastSendMsgGroupEqDate.msgList.push(msgToSend)
      }

      this.msgToSend.msg = ''
    },
    changeMsgToHTMLFormat(msg) {

      return msg.replaceAll('\n', '<br />')
    }
  }
}
</script>