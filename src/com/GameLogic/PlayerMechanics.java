package com.GameLogic;

import com.Items.Armor;
import com.Items.Item;
import com.Items.Weapons;
import com.Players.Player;
import com.Rooms.Room;
import com.Story.Story;
import com.Utility.Printer;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerMechanics implements java.io.Serializable{

    public static void moveRoom(String location, Game game) throws IOException, ParseException, InterruptedException {
        Player player = game.getPlayer();
        List<Room> rooms = game.map.stream().filter(room -> room.getName().equalsIgnoreCase(location)).collect(Collectors.toList());
        if (rooms.size() == 0) {
            Printer.print(Story.noRoomMessage());
            game.playGame();
        }
        player.setCurrentRoom(rooms.get(0));
    }

    public static void lookAround(Game game){
        Player player = game.getPlayer();
        Room currentRoom = player.getCurrentRoom();
        System.out.println("You see:");
        ArrayList<Item> roomItems = (ArrayList<Item>) currentRoom.getItems();
        for(int x=0; x< currentRoom.getItems().size(); x++){
            Item s = roomItems.get(x);
            System.out.println(s.getName());
        }
        System.out.println("\nEnemies");
        if (currentRoom.getEnemy() != null) {
            System.out.println(currentRoom.getEnemy().getName());
        } else {
            System.out.println("There are no enemies here.");
        }


    }

    public static void lookItem(String item, Collection<Item> roomItems, Collection<Item> playerItems){
        List<Item> itemsInInventory = playerItems.stream().filter(ite -> ite.getName().equalsIgnoreCase(item)).collect(Collectors.toList());
        if(itemsInInventory.size() > 0) {
            Item lookedAt = itemsInInventory.get(0);
            System.out.println("This is a " + lookedAt.getName()+"." + lookedAt.getDesc());
        }
        List<Item> itemToLookAt = roomItems.stream().filter(ite -> ite.getName().equalsIgnoreCase(item)).collect(Collectors.toList());
        Item lookedAt = itemToLookAt.get(0);
        System.out.println("This is a " + lookedAt.getName()+"." + lookedAt.getDesc());
    }




    public static void getItem(String item, Collection<Item> roomItems, Collection<Item> playerItems) {
        List<Item> itemToGrab = roomItems.stream().filter(ite -> ite.getName().equalsIgnoreCase(item)).collect(Collectors.toList());
        Item taken = itemToGrab.get(0);
        roomItems.remove(taken);
        playerItems.add(taken);
        System.out.println("You picked up the " + taken.getName() + "!");
    }

    public static void dropItem(String item, Collection<Item> roomItems, Collection<Item> playerItems) {
        List<Item> itemToGrab = playerItems.stream().filter(ite -> ite.getName().equalsIgnoreCase(item)).collect(Collectors.toList());
        Item taken = itemToGrab.get(0);
        playerItems.remove(taken);
        roomItems.add(taken);
        System.out.println("You dropped the " + taken.getName() + "!");
    }

    public static void checkInventory(Player player1) throws IOException, ParseException, InterruptedException {
        if (player1.getItems().size() == 0) {
            Printer.print(Story.nothingInInventory());}
        else{
            for (Item item: player1.getItems()) {
                System.out.println(item.getName());
            }
        }

    }

    public static void lookAtMap(Game game) {
        for (Room room: game.map){
            System.out.println(room.getName());
        }
    }
    public static void stats(Player player) {
        System.out.println("Name: "+player.getName());
        System.out.println("HP: "+player.getHp());
        System.out.println("Attack Power: "+ player.getAttack());
        if (player.getEquippedWeapon() != null) {
            System.out.println("Equipped Weapon: " + player.getEquippedWeapon().getName());
        } else {
            System.out.println("You don't have an equipped Weapon");
        }

        if (player.getEquippedArmor() != null) {
            System.out.println("Equipped Armor: " + player.getEquippedArmor().getName());
        } else {
            System.out.println("You don't have any Armor equipped");
        }
    }

    public static void equipWeapon(Player player, String item) {
        List<Item> inventory = (List<Item>) player.getItems().stream().filter(ite -> ite.getName().equalsIgnoreCase(item)).collect(Collectors.toList());
        if (inventory.size() == 0) {
            System.out.println("You don't have a weapon with that name");
        } else {
            if (player.getEquippedWeapon() != null) {
                player.setAttack(player.getAttack()-player.getEquippedWeapon().getAttack());
                player.setEquippedWeapon(null);
            }
            System.out.println("You equipped your: " + inventory.get(0).getName());
            player.setEquippedWeapon((Weapons) inventory.get(0));
            player.setAttack(player.getAttack() + ((Weapons) inventory.get(0)).getAttack());
        }
    }

    public static void equipArmor(Player player, String item) {
        List<Item> inventory = (List<Item>) player.getItems().stream().filter(ite -> ite.getName().equalsIgnoreCase(item)).collect(Collectors.toList());
        if (inventory.size() == 0) {
            System.out.println("You don't have Armor with that name");
        } else {
            if (player.getEquippedArmor() != null) {
                player.setHp(player.getHp()-player.getEquippedArmor().getDefence());
                player.setEquippedArmor(null);
            }
            System.out.println("You equipped your: " + inventory.get(0).getName());
            player.setEquippedArmor((Armor) inventory.get(0));
            player.setHp(player.getHp() + ((Armor) inventory.get(0)).getDefence());
        }
    }

    public static boolean checkInstance(Player player,String item) {
        List<Item> inventoryItem = (List<Item>) player.getItems().stream().filter(ite -> ite.getName().equalsIgnoreCase(item)).collect(Collectors.toList());
        return inventoryItem.get(0) instanceof Weapons;
    }


}
