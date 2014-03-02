package org.spacehq.mcprotocol.event;

import org.spacehq.mcprotocol.standard.packet.PacketAnimation;
import org.spacehq.mcprotocol.standard.packet.PacketAttachEntity;
import org.spacehq.mcprotocol.standard.packet.PacketBlockAction;
import org.spacehq.mcprotocol.standard.packet.PacketBlockBreakAnimation;
import org.spacehq.mcprotocol.standard.packet.PacketBlockChange;
import org.spacehq.mcprotocol.standard.packet.PacketChat;
import org.spacehq.mcprotocol.standard.packet.PacketClientInfo;
import org.spacehq.mcprotocol.standard.packet.PacketClientStatus;
import org.spacehq.mcprotocol.standard.packet.PacketCloseWindow;
import org.spacehq.mcprotocol.standard.packet.PacketCollectItem;
import org.spacehq.mcprotocol.standard.packet.PacketConfirmTransaction;
import org.spacehq.mcprotocol.standard.packet.PacketCreativeSlot;
import org.spacehq.mcprotocol.standard.packet.PacketDestroyEntity;
import org.spacehq.mcprotocol.standard.packet.PacketDisconnect;
import org.spacehq.mcprotocol.standard.packet.PacketDisplayScoreboard;
import org.spacehq.mcprotocol.standard.packet.PacketEffect;
import org.spacehq.mcprotocol.standard.packet.PacketEnchantItem;
import org.spacehq.mcprotocol.standard.packet.PacketEntity;
import org.spacehq.mcprotocol.standard.packet.PacketEntityAction;
import org.spacehq.mcprotocol.standard.packet.PacketEntityAttributes;
import org.spacehq.mcprotocol.standard.packet.PacketEntityEffect;
import org.spacehq.mcprotocol.standard.packet.PacketEntityEquipment;
import org.spacehq.mcprotocol.standard.packet.PacketEntityHeadYaw;
import org.spacehq.mcprotocol.standard.packet.PacketEntityLook;
import org.spacehq.mcprotocol.standard.packet.PacketEntityLookRelativeMove;
import org.spacehq.mcprotocol.standard.packet.PacketEntityMetadata;
import org.spacehq.mcprotocol.standard.packet.PacketEntityRelativeMove;
import org.spacehq.mcprotocol.standard.packet.PacketEntityStatus;
import org.spacehq.mcprotocol.standard.packet.PacketEntityTeleport;
import org.spacehq.mcprotocol.standard.packet.PacketEntityVelocity;
import org.spacehq.mcprotocol.standard.packet.PacketExplosion;
import org.spacehq.mcprotocol.standard.packet.PacketGameState;
import org.spacehq.mcprotocol.standard.packet.PacketHandshake;
import org.spacehq.mcprotocol.standard.packet.PacketHealthUpdate;
import org.spacehq.mcprotocol.standard.packet.PacketHeldItemChange;
import org.spacehq.mcprotocol.standard.packet.PacketIncrementStatistic;
import org.spacehq.mcprotocol.standard.packet.PacketItemData;
import org.spacehq.mcprotocol.standard.packet.PacketKeepAlive;
import org.spacehq.mcprotocol.standard.packet.PacketKeyRequest;
import org.spacehq.mcprotocol.standard.packet.PacketKeyResponse;
import org.spacehq.mcprotocol.standard.packet.PacketLightning;
import org.spacehq.mcprotocol.standard.packet.PacketLogin;
import org.spacehq.mcprotocol.standard.packet.PacketMapChunk;
import org.spacehq.mcprotocol.standard.packet.PacketMapChunkBulk;
import org.spacehq.mcprotocol.standard.packet.PacketMultiBlockChange;
import org.spacehq.mcprotocol.standard.packet.PacketNamedSound;
import org.spacehq.mcprotocol.standard.packet.PacketOpenTileEditor;
import org.spacehq.mcprotocol.standard.packet.PacketOpenWindow;
import org.spacehq.mcprotocol.standard.packet.PacketPlayer;
import org.spacehq.mcprotocol.standard.packet.PacketPlayerAbilities;
import org.spacehq.mcprotocol.standard.packet.PacketPlayerBlockPlace;
import org.spacehq.mcprotocol.standard.packet.PacketPlayerDigging;
import org.spacehq.mcprotocol.standard.packet.PacketPlayerListItem;
import org.spacehq.mcprotocol.standard.packet.PacketPlayerLook;
import org.spacehq.mcprotocol.standard.packet.PacketPlayerPosition;
import org.spacehq.mcprotocol.standard.packet.PacketPlayerPositionLook;
import org.spacehq.mcprotocol.standard.packet.PacketPluginMessage;
import org.spacehq.mcprotocol.standard.packet.PacketRemoveEntityEffect;
import org.spacehq.mcprotocol.standard.packet.PacketRespawn;
import org.spacehq.mcprotocol.standard.packet.PacketScoreboardObjective;
import org.spacehq.mcprotocol.standard.packet.PacketServerPing;
import org.spacehq.mcprotocol.standard.packet.PacketSetExperience;
import org.spacehq.mcprotocol.standard.packet.PacketSetSlot;
import org.spacehq.mcprotocol.standard.packet.PacketSpawnExpOrb;
import org.spacehq.mcprotocol.standard.packet.PacketSpawnMob;
import org.spacehq.mcprotocol.standard.packet.PacketSpawnNamedEntity;
import org.spacehq.mcprotocol.standard.packet.PacketSpawnObject;
import org.spacehq.mcprotocol.standard.packet.PacketSpawnPainting;
import org.spacehq.mcprotocol.standard.packet.PacketSpawnParticle;
import org.spacehq.mcprotocol.standard.packet.PacketSpawnPosition;
import org.spacehq.mcprotocol.standard.packet.PacketSteerVehicle;
import org.spacehq.mcprotocol.standard.packet.PacketTabComplete;
import org.spacehq.mcprotocol.standard.packet.PacketTeam;
import org.spacehq.mcprotocol.standard.packet.PacketTimeUpdate;
import org.spacehq.mcprotocol.standard.packet.PacketUpdateScoreboard;
import org.spacehq.mcprotocol.standard.packet.PacketUpdateSign;
import org.spacehq.mcprotocol.standard.packet.PacketUpdateTileEntity;
import org.spacehq.mcprotocol.standard.packet.PacketUseBed;
import org.spacehq.mcprotocol.standard.packet.PacketUseEntity;
import org.spacehq.mcprotocol.standard.packet.PacketWindowClick;
import org.spacehq.mcprotocol.standard.packet.PacketWindowItems;
import org.spacehq.mcprotocol.standard.packet.PacketWindowProperty;

/**
 * Empty implementation of the PacketVisitor interface for convenience. Usually
 * used when most methods are not implemented.
 * 
 * @author dconnor
 */
public class PacketVisitorAdapter implements PacketVisitor {

	public void visit(PacketKeepAlive packet) {
	}

	public void visit(PacketLogin packet) {
	}

	public void visit(PacketHandshake packet) {
	}

	public void visit(PacketChat packet) {
	}

	public void visit(PacketTimeUpdate packet) {
	}

	public void visit(PacketEntityEquipment packet) {
	}

	public void visit(PacketSpawnPosition packet) {
	}

	public void visit(PacketUseEntity packet) {
	}

	public void visit(PacketHealthUpdate packet) {
	}

	public void visit(PacketRespawn packet) {
	}

	public void visit(PacketPlayer packet) {
	}

	public void visit(PacketPlayerPosition packet) {
	}

	public void visit(PacketPlayerLook packet) {
	}

	public void visit(PacketPlayerPositionLook packet) {
	}

	public void visit(PacketPlayerDigging packet) {
	}

	public void visit(PacketPlayerBlockPlace packet) {
	}

	public void visit(PacketHeldItemChange packet) {
	}

	public void visit(PacketUseBed packet) {
	}

	public void visit(PacketAnimation packet) {
	}

	public void visit(PacketEntityAction packet) {
	}

	public void visit(PacketSpawnNamedEntity packet) {
	}

	public void visit(PacketCollectItem packet) {
	}

	public void visit(PacketSpawnObject packet) {
	}

	public void visit(PacketSpawnMob packet) {
	}

	public void visit(PacketSpawnPainting packet) {
	}

	public void visit(PacketSpawnExpOrb packet) {
	}

	public void visit(PacketSteerVehicle packet) {
	}

	public void visit(PacketEntityVelocity packet) {
	}

	public void visit(PacketDestroyEntity packet) {
	}

	public void visit(PacketEntity packet) {
	}

	public void visit(PacketEntityRelativeMove packet) {
	}

	public void visit(PacketEntityLook packet) {
	}

	public void visit(PacketEntityLookRelativeMove packet) {
	}

	public void visit(PacketEntityTeleport packet) {
	}

	public void visit(PacketEntityHeadYaw packet) {
	}

	public void visit(PacketEntityStatus packet) {
	}

	public void visit(PacketAttachEntity packet) {
	}

	public void visit(PacketEntityMetadata packet) {
	}

	public void visit(PacketEntityEffect packet) {
	}

	public void visit(PacketRemoveEntityEffect packet) {
	}

	public void visit(PacketSetExperience packet) {
	}

	public void visit(PacketEntityAttributes packet) {
	}

	public void visit(PacketMapChunk packet) {
	}

	public void visit(PacketMultiBlockChange packet) {
	}

	public void visit(PacketBlockChange packet) {
	}

	public void visit(PacketBlockAction packet) {
	}

	public void visit(PacketBlockBreakAnimation packet) {
	}

	public void visit(PacketMapChunkBulk packet) {
	}

	public void visit(PacketExplosion packet) {
	}

	public void visit(PacketEffect packet) {
	}

	public void visit(PacketNamedSound packet) {
	}

	public void visit(PacketSpawnParticle packet) {
	}

	public void visit(PacketGameState packet) {
	}

	public void visit(PacketLightning packet) {
	}

	public void visit(PacketOpenWindow packet) {
	}

	public void visit(PacketCloseWindow packet) {
	}

	public void visit(PacketWindowClick packet) {
	}

	public void visit(PacketSetSlot packet) {
	}

	public void visit(PacketWindowItems packet) {
	}

	public void visit(PacketWindowProperty packet) {
	}

	public void visit(PacketConfirmTransaction packet) {
	}

	public void visit(PacketCreativeSlot packet) {
	}

	public void visit(PacketEnchantItem packet) {
	}

	public void visit(PacketUpdateSign packet) {
	}

	public void visit(PacketItemData packet) {
	}

	public void visit(PacketUpdateTileEntity packet) {
	}

	public void visit(PacketOpenTileEditor packet) {
	}

	public void visit(PacketIncrementStatistic packet) {
	}

	public void visit(PacketPlayerListItem packet) {
	}

	public void visit(PacketPlayerAbilities packet) {
	}

	public void visit(PacketTabComplete packet) {
	}

	public void visit(PacketClientInfo packet) {
	}

	public void visit(PacketClientStatus packet) {
	}

	public void visit(PacketScoreboardObjective packet) {
	}

	public void visit(PacketUpdateScoreboard packet) {
	}

	public void visit(PacketDisplayScoreboard packet) {
	}

	public void visit(PacketTeam packet) {
	}

	public void visit(PacketPluginMessage packet) {
	}

	public void visit(PacketKeyResponse packet) {
	}

	public void visit(PacketKeyRequest packet) {
	}

	public void visit(PacketServerPing packet) {
	}

	public void visit(PacketDisconnect packet) {
	}
}
