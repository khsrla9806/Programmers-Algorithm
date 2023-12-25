class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        
        Player player = new Player(health, bandage);
        
        int[] playInfo = new int[attacks[attacks.length - 1][0] + 1];
        
        for (int[] attack : attacks) {
            playInfo[attack[0]] = attack[1];
        }
        
        for (int i = 1; i < playInfo.length; i++) {
            if (player.isDead) {
                return -1;
            }
            if (playInfo[i] == 0) {
                player.heal();
            } else if (playInfo[i] > 0) {
                player.attacked(playInfo[i]);
            }
        }
        
        return player.isDead ? -1 : player.health;
    }
}

class Player {
    int maxHealth;
    int health;
    int bandageStack;
    boolean isDead;
    int[] bandage;
    
    public Player(int health, int[] bandage) {
        this.maxHealth = health;
        this.health = health;
        this.bandage = bandage;
    }
    
    /**
     * 체력 회복
     */
    public void heal() {
        bandageStack++;
        health += bandage[1];
        additionalHeal();
        checkMaxHealth();
    }
    
    private void additionalHeal() {
        if (bandageStack >= bandage[0]) {
            bandageStack = 0;
            health += bandage[2];
        }
    }
    
    private void checkMaxHealth() {
        if (health > maxHealth) {
            health = maxHealth;
        }
    }
    
    /**
     * 공격 당함
     */
    public void attacked(int damage) {
        bandageStack = 0;
        health -= damage;
        isDead = health <= 0;
    }
}