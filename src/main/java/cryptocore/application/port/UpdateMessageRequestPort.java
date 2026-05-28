package cryptocore.application.port;

import cryptocore.application.model.UpdateChat;

public interface UpdateMessageRequestPort {
    void updatedMessage(UpdateChat updateChat);
}
