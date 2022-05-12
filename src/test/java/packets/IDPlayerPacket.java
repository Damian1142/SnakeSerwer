package packets;


public class IDPlayerPacket extends PlayerPacket {

    public static long serialVersionUID = 3L;
    public IDPlayerPacket(int id, String name) {
        super(id, name);
    }
}
