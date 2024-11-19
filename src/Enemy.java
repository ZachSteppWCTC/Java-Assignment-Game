/**
 * A subclass of character including all unique properties of enemies and actions done by them
 * @author Zach Stepp
 */
public class Enemy extends Character{
    private final double basehitchance;
    private final int xpGain;
    private final Item mainDrop;
    private final double mainDropChance;
    private final Item rareDrop;
    private final double rareDropChance;

        public Enemy(String name, int maxhp, int damage, double basehitchance, int xpGain, Item mainDrop, double mainDropChance, Item rareDrop, double rareDropChance) {
            super(name, maxhp);
            setDamage(damage);
            this.basehitchance = basehitchance;
            this.xpGain = xpGain;
            this.mainDrop = mainDrop;
            this.mainDropChance = mainDropChance;
            this.rareDrop = rareDrop;
            this.rareDropChance = rareDropChance;
        }
        /**
         * Attacks the player and checks to see if they are still alive. If the attack lands and the player is killed,
         * the player's health is set to 0 in case of damage overflow, and their health is printed in a message and
         * true is returned. If the attack misses or the attack doesn't kill the player, then false is returned.
         * @return true if player's health is 0 after an attack, otherwise false
         */
        public boolean killedPlayer(Player player) {
            if (hitplayer(player)) {
                if (player.getHp() <= 0) {
                    player.setHp(0);
                    Messages.printhealth(player);
                    return true;
                } else {
                    Messages.printhealth(player);
                    return false;
                }
            } else {
                return false;
            }
        }
        /**
         * Whether the enemy lands their hit on the player is determined by the player's defense and a
         * base hit determined by enemy when it is created.
         * @return true if the enemy lands an attack on the player, false if not.
         */
        public boolean hitplayer(Player player) {
            if (Math.random() < basehitchance - (0.01 * player.getDefense())) {
                player.setHp(player.getHp() - getDamage());
                Messages.hit(this, player);
                return true;
            } else {
                Messages.miss(this);
                return false;
            }
        }

        public int getXpGain() {
            return xpGain;
        }

        public Item getMainDrop() {
            return mainDrop;
        }

        public double getMainDropChance() {
            return mainDropChance;
        }

        public Item getRareDrop() {
            return rareDrop;
        }

        public double getRareDropChance() {
            return rareDropChance;
        }

        public double getBasehitchance() {
            return basehitchance;
        }
}