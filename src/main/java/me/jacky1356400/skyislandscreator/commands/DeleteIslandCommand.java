package me.jacky1356400.skyislandscreator.commands;

import me.jacky1356400.skyislandscreator.SkyIslandsCreator;
import me.jacky1356400.skyislandscreator.island.IslandUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class DeleteIslandCommand extends CommandBase implements ICommand {

    private List<String> aliases;

    public DeleteIslandCommand() {
        aliases = new ArrayList<>();
        aliases.add("skyislands_delete");
        aliases.add("skyisland_delete");
        aliases.add("skyislands_del");
        aliases.add("skyisland_del");
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public String getName() {
        return aliases.get(0);
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "skyislands_delete <IslandName>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] input) throws CommandException {
        if (input.length == 0) {
            sender.sendMessage(new TextComponentString("Invalid arguments!"));
        } else {
            EntityPlayerMP player = getPlayer(server, sender, sender.getName());
            if (!player.getEntityWorld().isRemote) {
                IslandUtils.deleteIsland(input[0]);
                player.sendMessage(new TextComponentString(String.format("Successfully deleted island %s", input[0])));
            } else {
                IslandUtils.deleteIsland(input[0]);
                SkyIslandsCreator.logger.info(String.format("Successfully deleted island %s", input[0]));
            }
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] input, BlockPos targetPos) {
        return input.length == 1 ? getListOfStringsMatchingLastWord(input, server.getServer().getOnlinePlayerNames())
                : null;
    }

}
