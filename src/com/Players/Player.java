package com.Players;

import com.Items.Armor;
import com.Items.Item;
import com.Items.Weapons;
import com.Rooms.Room;

import java.util.ArrayList;
import java.util.Collection;


public class Player {
    private String name;
    private long hp;
    private long attack;
    private Collection<Item> items = new ArrayList<>();
    private Room currentRoom;
    private Weapons equippedWeapon;
    private Armor equippedArmor;


    public Player(String name, long hp, long attack) {
        setName(name);
        setHp(hp);
        setAttack(attack);
    }

    public long getHp() {
        return hp;
    }

    public void setHp(long hp) {
        this.hp = hp;
    }

    public long getAttack() {
        return attack;
    }

    public void setAttack(long attack) {
        this.attack = attack;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weapons getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapons equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public void setEquippedArmor(Armor equippedArmor) {
        this.equippedArmor = equippedArmor;
    }

    public Player battle(Player enemy) {
        enemy.setHp(enemy.getHp()-getAttack());
        return enemy;
    }


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", attack=" + attack +
                '}';
    }
}
