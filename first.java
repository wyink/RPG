import java.io.*;
import java.util.Random;

/*
    �퓬�����s����N���X
*/
public class first{
    public static void main(String[] args) throws IOException{

        System.out.println("\n�퓬�J�n�I\n");
        
        //�L�����N�^�[�̍쐬
        Seraph you = new Seraph(50,3,5,5);
        Enemy  enemy = new Enemy();//default;

        //�����X�e�[�^�X�̕\��
        utility.showStatus(you, enemy);

        
        //���͗U��
        System.out.println("�U��=>y �Ȃɂ����Ȃ�=>n:\n");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String selectedNum = br.readLine();

        //�U��
        you.attack(enemy);
        enemy.attack(you);

        
        if(selectedNum=="y"){//�U����I��
            
            System.out.println("�U���I");
            
            //���Ȃ��̍U��
            you.attack(enemy);
            
            //���G�̔���
            enemy.attack(you);

            //�X�e�[�^�X�̕\��
            utility.showStatus(you, enemy);

        }else{
            //�������Ȃ�
        }

        //�Q��ڈȍ~
        while(you.getHp()>0 && enemy.getHp()>0){
            System.out.println("========================================");
            System.out.println("�\�͂��ꎞ�I�ɏ㏸�����邱�Ƃ��ł��܂��B(���p��������)\n");
            System.out.println("1.�U����>>�~2 and �h��>>�~0.5 \n2.�U����>>�~3 and �h���>>�~1/3\n3. �ʏ�U��\n");
            BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
            String selectedNum2 = br2.readLine();

            //���Ȃ��̑I���ɂ��U��
            int be4YourAtk = you.getAtk(); //�X�V�O�U����
            int be4YourDef = you.getDef(); //�X�V�O�h���
            
            boolean retryFlag = false;//���͂����҂������l�o�Ȃ������ꍇ
            switch (selectedNum2){
                case "1"://1.�U����>>�~2 and �h��>>�~0.5
                    you.setAtk(be4YourAtk*3);
                    you.setDef(be4YourDef/2);
                    break;
                case "2"://2.�U����>>�~3 and �h���>>�~1/3 and Hp>>-7
                you.setAtk(be4YourAtk*3);
                you.setDef(be4YourDef/3);
                you.setHp(you.getHp()-7);
                    break;
                case "3":
                    break;
                default:
                    System.out.println("���҂������͂ł͂���܂���B");
                    retryFlag = true;
                    break;
            }
            if(retryFlag==true){
                continue;}

            //�G�̑I���ɂ��U��
            Random random = new Random();
            Integer randomValue = random.nextInt(3);
            String randomValueString = randomValue.toString();
            int be4EneAtk = enemy.getAtk(); //�X�V�O�U����
            int be4EneDef = enemy.getDef(); //�X�V�O�h���

            switch (randomValueString){
                case "1"://1.�U����>>�~2 and �h��>>�~0.5
                    enemy.setAtk(be4EneAtk*2);
                    enemy.setDef(be4EneDef/2);
                    break;
                case "2"://2.�U����>>�~3 and �h���>>�~1/3 Hp>>-7
                    enemy.setAtk(be4EneAtk*3);
                    enemy.setDef(be4EneDef/3);
                    enemy.setHp(enemy.getHp()-7);
                    break;
                case "3":
                    break;
            }


            //�U��
            you.attack(enemy);

            if(enemy.getHp()<0){
                System.out.println("You win\n");
                break;
            }

            //�G�̍U��
            enemy.attack(you);
            if(you.getHp()<0){
                System.out.println("You lose\n");
                break;
            }
            
            utility.showStatus(you, enemy);

            //�X�V�O�̏�Ԃɖ߂�
            you.setAtk(be4YourAtk);
            you.setDef(be4YourDef);

            enemy.setAtk(be4EneAtk);
            enemy.setDef(be4EneDef);

            System.out.println("�퓬�͑��s���ł��B�ipress any key...)");
            BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));
            br3.readLine();
    
        }
    }
}

class utility{

    //�C���X�^���X���͋֎~
    private utility(){}

    //�X�e�[�^�X�̕\��
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
    �����Ƃ���������̂̌��^
*/
abstract class Character{
    private int hp;       //HP
    //int type;   //�^�C�v�i�����ɂ���ĕω��j
    protected int dp;         //�K�[�h
    protected int atk;    //�U����
    protected int def;  //�h���

    public Character(){
        this.hp = 50;
        //this.dp = 5;
        this.atk = 5;
        this.def = 5;
    }
    //�R���X�g���N�^
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


    //�U���͊e�L�����ŋ�̓I�Ɏ�������B
    protected abstract void attack(Character character);
}

/*
    �Z���t
*/
class Seraph extends Character{

    public Seraph(int hp,int dp,int atk,int def){
        super(hp,dp,atk,def);
    }

    @Override
    public void attack(Character character){
        int enemyHp = character.getHp();

        //���g�̍U���͂ƓG�̖h��͂��l�����ēG��HP�����炷
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

        //���g�̍U���͂ƓG�̖h��͂��l�����ēG��HP�����炷
        character.setHp(seraphHp-this.atk/2-character.def);

    }
}