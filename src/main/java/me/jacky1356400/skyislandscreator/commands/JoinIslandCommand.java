package me.jacky1356400.skyislandscreator.commands;

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

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class JoinIslandCommand extends CommandBase implements ICommand {

    private List<String> aliases;

    public JoinIslandCommand() {
        aliases = new ArrayList<>();
        aliases.add("skyislands_join");
        aliases.add("skyisland_join");
    }

    @Override
    public List getCommandAliases() {
        return aliases;
    }

    @Override
    public String getCommandName() {
        return aliases.get(0);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "skyislands_join <IslandName>";
    }
    
    @Override
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] input, @Nullable BlockPos pos) {
        return input.length == 1 ? getListOfStringsMatchingLastWord(input, server.getServer().getAllUsernames())
                : null;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] input) throws CommandException {
        World world = sender.getEntityWorld();
        EntityPlayerMP player = (EntityPlayerMP) world.getPlayerEntityByName(sender.getName());
        if (input.length == 0) {
            sender.addChatMessage(new TextComponentString("Invalid arguments!"));
        } else {
            IslandUtils.joinIsland(input[0], player);
        }
    }

}