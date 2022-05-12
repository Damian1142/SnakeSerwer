package packets;

public class RemovePlayerPacket extends PlayerPacket {

    public static long serialVersionUID = 4L;
    public RemovePlayerPacket(int id, String name) {
        super(id, name);
    }
}
