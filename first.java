import java.io.*;
import java.util.Random;

/*
    í“¬‚ğÀs‚·‚éƒNƒ‰ƒX
*/
public class first{
    public static void main(String[] args) throws IOException{

        System.out.println("\ní“¬ŠJnI\n");
        
        //ƒLƒƒƒ‰ƒNƒ^[‚Ìì¬
        Seraph you = new Seraph(50,3,5,5);
        Enemy  enemy = new Enemy();//default;

        //‰ŠúƒXƒe[ƒ^ƒX‚Ì•\¦
        utility.showStatus(you, enemy);

        
        //“ü—Í—U“±
        System.out.println("UŒ‚=>y ‚È‚É‚à‚µ‚È‚¢=>n:\n");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String selectedNum = br.readLine();

        //UŒ‚
        you.attack(enemy);
        enemy.attack(you);

        
        if(selectedNum=="y"){//UŒ‚‚ğ‘I‘ğ
            
            System.out.println("UŒ‚I");
            
            //‚ ‚È‚½‚ÌUŒ‚
            you.attack(enemy);
            
            //–“G‚Ì”½Œ‚
            enemy.attack(you);

            //ƒXƒe[ƒ^ƒX‚Ì•\¦
            utility.showStatus(you, enemy);

        }else{
            //‰½‚à‚µ‚È‚¢
        }

        //‚Q‰ñ–ÚˆÈ~
        while(you.getHp()>0 && enemy.getHp()>0){
            System.out.println("========================================");
            System.out.println("”\—Í‚ğˆê“I‚Éã¸‚³‚¹‚é‚±‚Æ‚ª‚Å‚«‚Ü‚·B(”¼Šp”š“ü—Í)\n");
            System.out.println("1.UŒ‚—Í>>~2 and –hŒä>>~0.5 \n2.UŒ‚—Í>>~3 and –hŒä—Í>>~1/3\n3. ’ÊíUŒ‚\n");
            BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
            String selectedNum2 = br2.readLine();

            //‚ ‚È‚½‚Ì‘I‘ğ‚É‚æ‚éUŒ‚
            int be4YourAtk = you.getAtk(); //XV‘OUŒ‚—Í
            int be4YourDef = you.getDef(); //XV‘O–hŒä—Í
            
            boolean retryFlag = false;//“ü—Í‚ªŠú‘Ò‚µ‚½”’lo‚È‚©‚Á‚½ê‡
            switch (selectedNum2){
                case "1"://1.UŒ‚—Í>>~2 and –hŒä>>~0.5
                    you.setAtk(be4YourAtk*3);
                    you.setDef(be4YourDef/2);
                    break;
                case "2"://2.UŒ‚—Í>>~3 and –hŒä—Í>>~1/3 and Hp>>-7
                you.setAtk(be4YourAtk*3);
                you.setDef(be4YourDef/3);
                you.setHp(you.getHp()-7);
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Šú‘Ò‚µ‚½“ü—Í‚Å‚Í‚ ‚è‚Ü‚¹‚ñB");
                    retryFlag = true;
                    break;
            }
            if(retryFlag==true){
                continue;}

            //“G‚Ì‘I‘ğ‚É‚æ‚éUŒ‚
            Random random = new Random();
            Integer randomValue = random.nextInt(3);
            String randomValueString = randomValue.toString();
            int be4EneAtk = enemy.getAtk(); //XV‘OUŒ‚—Í
            int be4EneDef = enemy.getDef(); //XV‘O–hŒä—Í

            switch (randomValueString){
                case "1"://1.UŒ‚—Í>>~2 and –hŒä>>~0.5
                    enemy.setAtk(be4EneAtk*2);
                    enemy.setDef(be4EneDef/2);
                    break;
                case "2"://2.UŒ‚—Í>>~3 and –hŒä—Í>>~1/3 Hp>>-7
                    enemy.setAtk(be4EneAtk*3);
                    enemy.setDef(be4EneDef/3);
                    enemy.setHp(enemy.getHp()-7);
                    break;
                case "3":
                    break;
            }


            //UŒ‚
            you.attack(enemy);

            if(enemy.getHp()<0){
                System.out.println("You win\n");
                break;
            }

            //“G‚ÌUŒ‚
            enemy.attack(you);
            if(you.getHp()<0){
                System.out.println("You lose\n");
                break;
            }
            
            utility.showStatus(you, enemy);

            //XV‘O‚Ìó‘Ô‚É–ß‚·
            you.setAtk(be4YourAtk);
            you.setDef(be4YourDef);

            enemy.setAtk(be4EneAtk);
            enemy.setDef(be4EneDef);

            System.out.println("í“¬‚Í‘±s’†‚Å‚·Bipress any key...)");
            BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));
            br3.readLine();
    
        }
    }
}

class utility{

    //ƒCƒ“ƒXƒ^ƒ“ƒX‰»‚Í‹Ö~
    private utility(){}

    //ƒXƒe[ƒ^ƒX‚Ì•\¦
    public static void showStatus(Seraph you,Enemy enemy){

        System.out.println("Status--------------------------");
        
        System.out.println(
            "you   HP="+ you.getHp() + " ATK=" + you.getAtk() + " DEF=" + you.getDef() +"\n"
        );

        System.out.println(
            "enemy HP="+ enemy.getHp() + " ATK=" + enemy.getAtk() + " DEF=" + enemy.getDef() +"\n"
        );
        System.out.println("---------------------------------");


    }
}


/*
    ‚¢‚«‚Æ‚µ‚¢‚¯‚é‚à‚Ì‚ÌŒ´Œ^
*/
abstract class Character{
    private int hp;       //HP
    //int type;   //ƒ^ƒCƒvi‘Š«‚É‚æ‚Á‚Ä•Ï‰»j
    protected int dp;         //ƒK[ƒh
    protected int atk;    //UŒ‚—Í
    protected int def;  //–hŒä—Í

    public Character(){
        this.hp = 50;
        //this.dp = 5;
        this.atk = 5;
        this.def = 5;
    }
    //ƒRƒ“ƒXƒgƒ‰ƒNƒ^
    public Character(int hp,int dp,int atk,int def){
        this.hp = hp;
        //this.dp = dp;
        this.atk = atk;
        this.def = def;
    }

    public int getAtk(){return atk;}
    public void setAtk(int atk){this.atk = atk;}

    public int getHp(){return this.hp;}
    public void setHp(int hp){this.hp = hp;}

    public int getDef(){return this.def;}
    public void setDef(int def){this.def = def;}


    //UŒ‚‚ÍŠeƒLƒƒƒ‰‚Å‹ï‘Ì“I‚ÉÀ‘•‚·‚éB
    protected abstract void attack(Character character);
}

/*
    ƒZƒ‰ƒt
*/
class Seraph extends Character{

    public Seraph(int hp,int dp,int atk,int def){
        super(hp,dp,atk,def);
    }

    @Override
    public void attack(Character character){
        int enemyHp = character.getHp();

        //©g‚ÌUŒ‚—Í‚Æ“G‚Ì–hŒä—Í‚ğl—¶‚µ‚Ä“G‚ÌHP‚ğŒ¸‚ç‚·
        character.setHp(enemyHp-this.atk/2-character.def);
    }
}

class Enemy extends Character{

    public Enemy(){
        super();
    }
    public Enemy(int hp,int dp,int atk,int def){
        super(hp, dp, atk, def);
    }

    @Override
    public void attack(Character character){
        int seraphHp = character.getHp();

        //©g‚ÌUŒ‚—Í‚Æ“G‚Ì–hŒä—Í‚ğl—¶‚µ‚Ä“G‚ÌHP‚ğŒ¸‚ç‚·
        character.setHp(seraphHp-this.atk/2-character.def);

    }
}