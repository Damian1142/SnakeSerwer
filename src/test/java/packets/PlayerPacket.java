package packets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerPacket implements Serializable {

    public static long serialVersionUID = 1L;
    public int id;
    public String name;
}
