import java.io.*;
import java.util.Random;

/*
    戦闘を実行するクラス
*/
public class first{
    public static void main(String[] args) throws IOException{

        System.out.println("\n戦闘開始！\n");
        
        //キャラクターの作成
        Seraph you = new Seraph(50,3,5,5);
        Enemy  enemy = new Enemy();//default;

        //初期ステータスの表示
        utility.showStatus(you, enemy);

        
        //入力誘導
        System.out.println("攻撃=>y なにもしない=>n:\n");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String selectedNum = br.readLine();

        //攻撃
        you.attack(enemy);
        enemy.attack(you);

        
        if(selectedNum=="y"){//攻撃を選択
            
            System.out.println("攻撃！");
            
            //あなたの攻撃
            you.attack(enemy);
            
            //＊敵の反撃
            enemy.attack(you);

            //ステータスの表示
            utility.showStatus(you, enemy);

        }else{
            //何もしない
        }

        //２回目以降
        while(you.getHp()>0 && enemy.getHp()>0){
            System.out.println("========================================");
            System.out.println("能力を一時的に上昇させることができます。(半角数字入力)\n");
            System.out.println("1.攻撃力>>×2 and 防御>>×0.5 \n2.攻撃力>>×3 and 防御力>>×1/3\n3. 通常攻撃\n");
            BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
            String selectedNum2 = br2.readLine();

            //あなたの選択による攻撃
            int be4YourAtk = you.getAtk(); //更新前攻撃力
            int be4YourDef = you.getDef(); //更新前防御力
            
            boolean retryFlag = false;//入力が期待した数値出なかった場合
            switch (selectedNum2){
                case "1"://1.攻撃力>>×2 and 防御>>×0.5
                    you.setAtk(be4YourAtk*3);
                    you.setDef(be4YourDef/2);
                    break;
                case "2"://2.攻撃力>>×3 and 防御力>>×1/3 and Hp>>-7
                you.setAtk(be4YourAtk*3);
                you.setDef(be4YourDef/3);
                you.setHp(you.getHp()-7);
                    break;
                case "3":
                    break;
                default:
                    System.out.println("期待した入力ではありません。");
                    retryFlag = true;
                    break;
            }
            if(retryFlag==true){
                continue;}

            //敵の選択による攻撃
            Random random = new Random();
            Integer randomValue = random.nextInt(3);
            String randomValueString = randomValue.toString();
            int be4EneAtk = enemy.getAtk(); //更新前攻撃力
            int be4EneDef = enemy.getDef(); //更新前防御力

            switch (randomValueString){
                case "1"://1.攻撃力>>×2 and 防御>>×0.5
                    enemy.setAtk(be4EneAtk*2);
                    enemy.setDef(be4EneDef/2);
                    break;
                case "2"://2.攻撃力>>×3 and 防御力>>×1/3 Hp>>-7
                    enemy.setAtk(be4EneAtk*3);
                    enemy.setDef(be4EneDef/3);
                    enemy.setHp(enemy.getHp()-7);
                    break;
                case "3":
                    break;
            }


            //攻撃
            you.attack(enemy);

            if(enemy.getHp()<0){
                System.out.println("You win\n");
                break;
            }

            //敵の攻撃
            enemy.attack(you);
            if(you.getHp()<0){
                System.out.println("You lose\n");
                break;
            }
            
            utility.showStatus(you, enemy);

            //更新前の状態に戻す
            you.setAtk(be4YourAtk);
            you.setDef(be4YourDef);

            enemy.setAtk(be4EneAtk);
            enemy.setDef(be4EneDef);

            System.out.println("戦闘は続行中です。（press any key...)");
            BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));
            br3.readLine();
    
        }
    }
}

class utility{

    //インスタンス化は禁止
    private utility(){}

    //ステータスの表示
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
    いきとしいけるものの原型
*/
abstract class Character{
    private int hp;       //HP
    //int type;   //タイプ（相性によって変化）
    protected int dp;         //ガード
    protected int atk;    //攻撃力
    protected int def;  //防御力

    public Character(){
        this.hp = 50;
        //this.dp = 5;
        this.atk = 5;
        this.def = 5;
    }
    //コンストラクタ
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


    //攻撃は各キャラで具体的に実装する。
    protected abstract void attack(Character character);
}

/*
    セラフ
*/
class Seraph extends Character{

    public Seraph(int hp,int dp,int atk,int def){
        super(hp,dp,atk,def);
    }

    @Override
    public void attack(Character character){
        int enemyHp = character.getHp();

        //自身の攻撃力と敵の防御力を考慮して敵のHPを減らす
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

        //自身の攻撃力と敵の防御力を考慮して敵のHPを減らす
        character.setHp(seraphHp-this.atk/2-character.def);

    }
}