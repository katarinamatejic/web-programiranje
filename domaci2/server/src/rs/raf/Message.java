package rs.raf;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Message implements Serializable {

    private LocalDateTime dateOfMessage;
    private String message;
    private String sender;

    public Message(String message,String sender){
        this.dateOfMessage= LocalDateTime.now();
        this.message=message;
        this.sender=sender;
    }

    public LocalDateTime getDateOfMessage() {
        return dateOfMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setDateOfMessage(LocalDateTime dateOfMessage) {
        this.dateOfMessage = dateOfMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "dateOfMessage=" + dateOfMessage.toString() +
                ", message='" + message + '\'' +
                ", sender='" + sender + '\'' +
                '}';
    }
}
