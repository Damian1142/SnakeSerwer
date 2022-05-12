package packets;

public class AddPlayerPacket extends PlayerPacket {
    public static long serialVersionUID = 2L;
    public AddPlayerPacket(int id, String name) {
        super(id, name);
    }
}
