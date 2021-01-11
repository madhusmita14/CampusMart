package the.onlineservice.campusmart.Models;

public class ChatModel {
    String chat_tymTv,chat_nameTv;

    public ChatModel(){}

    public ChatModel(String chat_tymTv, String chat_nameTv) {
        this.chat_tymTv = chat_tymTv;
        this.chat_nameTv = chat_nameTv;
    }

    public String getChat_tymTv() {
        return chat_tymTv;
    }

    public void setChat_tymTv(String chat_tymTv) {
        this.chat_tymTv = chat_tymTv;
    }

    public String getChat_nameTv() {
        return chat_nameTv;
    }

    public void setChat_nameTv(String chat_nameTv) {
        this.chat_nameTv = chat_nameTv;
    }
}
