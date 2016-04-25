package com.github.hexosse.command;

/*
 * Copyright 2016 hexosse
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

import com.github.hexosse.WorldRestorer;
import com.github.hexosse.WorldRestorerApi;
import com.github.hexosse.configuration.Permissions;
import com.github.hexosse.pluginframework.pluginapi.PluginCommand;
import com.github.hexosse.pluginframework.pluginapi.command.CommandArgument;
import com.github.hexosse.pluginframework.pluginapi.command.CommandInfo;
import com.github.hexosse.pluginframework.pluginapi.command.type.ArgTypeWorld;
import com.github.hexosse.pluginframework.pluginapi.message.Message;
import com.github.hexosse.pluginframework.pluginapi.message.MessageTarget;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.World;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class WrCommandKickPlayers extends PluginCommand<WorldRestorer>
{
	public WrCommandKickPlayers(WorldRestorer plugin)
	{
		super("kickplayers", plugin);
		this.setAliases(Lists.newArrayList("kick", "k", "kp"));
		this.addArgument(new CommandArgument<World>("world", ArgTypeWorld.get(), true, true, plugin.messages.cKickArgWorld));
		this.setDescription(plugin.messages.cKick);
		this.setPermission(Permissions.KICK_PLAYERS.toString());
	}

	/**
	 * Executes the given command, returning its success
	 *
	 * @param commandInfo Info about the command
	 *
	 * @return true if a valid command, otherwise false
	 */
	@Override
	public boolean onCommand(CommandInfo commandInfo)
	{
		String worldName = commandInfo.getNamedArg("world");

		WorldRestorerApi.kickPlayersFromWorld(worldName, plugin.messages.sKickPlayer);

		// Message
		Message message = new Message();
		MessageTarget target = new MessageTarget(Bukkit.getConsoleSender()).add(commandInfo.getSender());
		message.setPrefix(plugin.messages.chatPrefix);
		message.add(new Message(plugin.messages.sKick.replace("{WORLD}",worldName)));
		messageManager.send(target, message);

		return true;
	}
}