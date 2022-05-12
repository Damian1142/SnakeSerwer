package com.mech.server.packets;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PlayerPacket implements Serializable {

    public static long serialVersionUID = 1L;
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private PlayerPacketType type;
    private String packet;
}
