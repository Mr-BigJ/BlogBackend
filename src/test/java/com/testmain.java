package com;

import cjj.Application;
import cjj.common.RedisUtil;
import cjj.dao.BlogContentMapper;
import cjj.dao.BlogUserMapper;
import cjj.dao.ResortUserMapper;
import cjj.entity.BlogContent;
import cjj.entity.BlogUser;
import cjj.service.serviceImpl.UserImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)//项目启动类
@WebAppConfiguration
public class testmain {

    @Resource
    UserImpl getUser;

    @Resource
    RedisUtil redisUtil;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    BlogContentMapper blogContentMapper;

    @Resource
    BlogUserMapper blogUserMapper;

    @Autowired
    ResortUserMapper resortUserMapper;


    @Test
    public void test1(){
        //2022-02-28 00:00:00
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = format.format(new Date());
        BlogContent blogContent = new BlogContent();
        blogContent.setCreated(date);
        blogContent.setStatus(1);
        List<String> list = new ArrayList<>();
        List<Integer> users = new ArrayList<>();
        list.add("如果你得罪了老板，失去的只是一份工作；如果你得罪了客户，失去的不过是一份订单；是的，世上只有一个人可以得罪：你给她脸色看，你冲她发牢骚，你大声顶撞她，甚至当 着她的面摔碗，她都不会记恨你，原因很简单，因为她是你的母亲");
        list.add("有位非常漂亮的女同事，有天起晚了没有时间化妆便急忙冲到公司。结果那天她被记旷工了……吃惊]");
        list.add("悟空和唐僧一起上某卫视非诚勿扰,悟空上台,24盏灯全灭。理由:1.没房没车只有一根破棍. 2.保镖职业危险.3.动不动打妖精,对女生不温柔. 4.坐过牢,曾被压五指山下500年。唐僧上台，哗!灯全亮。 理由:1.公务员； 2.皇上兄弟，后台最硬 3.精通梵文等外语 4.长得帅 5.最关键一点：有宝马！");
        list.add("Save your heart for someone who cares. 为了某个在乎你的人，请节约你的真心！");
        list.add("有一个人叫真咯嗦，娶了个老婆叫要你管，生了个儿子叫麻烦。有一天麻烦不见了！夫妻俩就去报案。警察问爸爸：请问这位男士你叫啥名字？爸爸说:真咯嗦。警察很生气，然后 他又问妈妈叫啥名字。妈妈说:要你管。警察非常生气的说:你们要干什么?夫妻俩说：找麻烦");
        list.add("一年奔波，尘缘遇了谁；一句珍重，天涯别了谁；一点灵犀，凭栏忆了谁；一种相思，闲愁予了谁；一江明月，豪情酬了谁；一场冬雪，烟波忘了谁；一壶浊酒，相逢醉了谁；一世浮生，轻狂撩了谁；一封短信，才情念了谁；一番思量，谁是谁的谁 ；一枚围脖，转发回复谁.....");
        list.add("金翠谢过后，多问了句：“先生，佛像怎么会在水里化了呢？”李先生小声说：“那尊白玉弥勒佛像是高人拿明矾做的，遇热就变形，遇水则溶化。之前我去观察过，那朝奉年纪大，一到晌午就打瞌睡，迷迷瞪瞪的，好几次出了差错。你这时候去呀，他不容易看出猫腻来。明白了吗？”");
        list.add("1.暧昧王:天蝎；2.犹豫不决:处女；3.朝三暮四:双子；4.逆向思维:水瓶；5.真爱无罪:双鱼；6.以史为鉴:摩羯；7.权衡利弊:金牛；8.宁缺毋滥:天秤；9.社会道德:巨蟹；10.惜时如金:白羊；11.无暇顾及:射手；12.目标明确:狮子。你是这样的吗？");
        list.add("白羊：记不清人；金牛：悔未上床；双子：他（她）是青蛙（恐龙）；巨蟹：善哉善哉；狮子：一脚蹬开；处女：倒大霉了；天秤：感觉很好；天蝎：你死我活；射手：有多少次；摩羯：气死我也；水瓶：奶奶个熊；双鱼：还是想他（她）{点开大图更精彩}");
        list.add("如果给你一次去西天取经的机会，选3个做你的徒弟，你会选谁？⒈多啦A梦。⒉柯南。⒊蜡笔小新。⒋樱木花道。⒌悟空。⒌大力水手。⒍超人。⒎功夫熊猫。⒏美少女战士。⒐路飞。⒑漩涡鸣人。⒒奥特曼。⒓卡卡西。⒔喜羊羊。⒕蜘蛛侠。⒖百变小樱。⒗忍者神龟。⒘变形金刚。⒙八神庵。⒚杀生丸。⒛阿童木");
        list.add("In October 2000， President Clinton authorized $16 million for the National Underground Railroad Freedom Center to honor this first great civil-rights struggle in the U。 S。 The center is scheduled to open in 2004 in Cincinnati。 And it's about time。 For the heroes of the Underground Railroad remain too little remembered， their exploits still largely unsung。 I was intent on telling their stories。");
        list.add("John Parker tensed when he heard the soft knock。 Peering out his door into the night， he recognized the face of a trusted neighbor。 \"There's a party of escaped slaves hiding in the woods in Kentucky， twenty miles from the river，\" the man whispered urgently。 Parker didn't hesitate。 \"I'll go，\" he said， pushing a pair of pistols into his pockets。");
        list.add("记者：说真的，你真的会给给孩子换尿布吗？ 姚明：要不你躺下，我给你换一个！实话告诉你，我用一只脚就能给孩子换尿布，喂奶啥的，都行。 记者：我不信！ 姚明：真的，连灯都不用开。 记者：不可能！你说怎么换？ 姚明：一只脚把媳妇揣醒就得。");
        list.add("一生只谈三次恋爱最好，一次懵懂，一次刻骨，一次一生。谈的太多会比较，无法确定；经历太多会麻木，不再相信爱情，行尸走肉，最后与不爱的人结婚，无法发自内心的爱对方，日常表现的应付，对方则抱怨你不够关心和不顾家，最后这失败的爱情，让你在遗憾和凑合中走完一生。");
        list.add("1吃饭的时候咬到自己的舌头。2在车上睡着了头敲到玻璃。3玩夹子，夹到手。4坐在椅子上摇椅子，把自己摔倒了。5左脚拌右脚结果摔倒。6碰到桌子或柜子的角，巨疼。7从身上硬撕下粘贴。8拉衣服拉链夹到下巴上的肉。9踩楼梯突然踩空摔倒。都经历过，那无敌了！");
        list.add("1友情:《牛仔裤的夏天》 2初恋:《情书》 3孤独:《天使爱美丽》 4坚强:《隐形的翅膀》 5成长:《千与千寻》 6自我:《穿普拉达的女王》 7尊严:《成为简奥斯汀》 8母爱:《黑暗中的舞者》 9自由:《蓝》 10智慧:《律政俏佳人》");
        list.add("有一种借口叫年轻，可以不珍惜时光，不珍惜爱，不珍惜一切来之不易的东西。有一种感情叫错过，错过爱，错过可以相守的人，错过一段刻骨铭心的情。有一种寂寞叫想念，想念一个人，一段往事，一场相遇。寂静的夜里，深深切切的想念，于是深深切切地寂寞……");
        list.add("我们常常看到的风景是：一个人总是仰望和羡慕着别人的幸福，一回头，却发现自己正被仰望和羡慕着。其实，每个人都是幸福的。只是，你的幸福，常常在别人眼里 。[阳光] 早安");
        list.add("你病，或者不病倒，老板就在那里，不悲不喜； 你休，或者不休假，工作就在那里，不来不去； 你拼，或者不拼命，工资就在那里，不增不减； 你辞，或者不辞职，地球还是会转，不歇不停； 让我中500万，或者 让我傍个大款， 扯淡，蛋疼，淡定，悲催，关注，评论，转发！！！");
        list.add("1、毕业后才知道校园恋爱是最纯洁的；2、毕业后才知道学习是最重要的；3、毕业后才知道校园生活是最幸福的；4、毕业后才知道宿舍生活是最好的；5、毕业后才知道食堂的饭菜是最便宜的；6、毕业后才知道上学是最美妙的事。7、毕业后才知道学生花钱最大手大脚......");
        list.add("“我爱你”的含义是：无论贫穷，富贵，生老，病死，天灾，人祸我都不离弃的爱你。当你说出这三个字的时候，你是否有足够的勇气和顽强的毅力去承受他的人生。爱，不要轻易说出口。");
        list.add("好好去爱，去生活。青春如此短暂，不要叹老。偶尔可以停下来休息，但是别蹲下来张望。走了一条路的时候，记得别回头看。时不时问问自己，自己在干嘛?记住，每天的太阳都 是新的，不要辜负了美好的晨光。");
        list.add("镜子前拉一下领带；吹口哨下楼；决然地摁灭香烟；突然把车开过来，摇下车窗笑；弯腰轻抚可爱小孩的头；从背后变出一朵玫瑰；做俯卧撑，满脸是汗珠；台上讲话眼睛扫视全场；思考时额头露出川字皱纹；果决大步流星地走；正在换灯泡或钉钉子；买单时毫不迟疑拿出钱包。");
        list.add("妈妈语重心长的对女儿说，“从小你就不聪明，累死累活的才考上个大学，毕业后还找不到工作，现在司机要男的、编辑要男的、会计要男的、连秘书也指定要男的，妈实在为你操碎了心啊。”女儿，“555555……。”妈妈一抹脸，坚定的说，“所以趁现在老婆还能是女人，赶紧上岗，要不然过两年……”");
        list.add("有时候，面对着身边的人，突然觉得说不出话。 有时候，曾经一直坚持的东西一夜间面目全非。 有时候，想放纵自己，希望自己痛痛快快歇斯底里地发一次疯。 有时候，别人突然对你说，我觉得你变了，然后自己开始百感交集。 有时候，觉得自己拥有着整个世界，一瞬间却又觉得自己其实一无所有。");
        list.add("男人就应该象自己的小弟弟， 第一 ：从不外露炫耀； 第二： 关键是时刻硬的起撑的住： 第三： 能培育出接班人； 第四： 善于攻击而又使其感到愉悦 ；第五： 既能制造摩擦又使大家同感快乐 ：第六： 胜利后能谦恭的缩小自己。 总结：低调、有骨气、有能力。。。(via@笑多了会怀孕)");
        list.add("语文好歹能增长你的文学知识！英语能让你与鬼佬交流！历史能让你不背叛啊！地理能让你不至于迷路啊！政治能让你知道怎样维权啊！可是数学除了毁掉整个人生还能做什么啊！泥马！！你用函数买菜啊！你去黄鹤楼还去算长江里的船距离你多远啊！你看到一排电话号码要想想它们之间有没有通项公式啊！@侯蒙恩");
        list.add("女生每个月都会来月经。又把来的那个称做\"好朋友\"，但知道为何要这样称呼呢？ 把好朋友这三字拆开不就很传神了吗? \"女子月月有\"!");
        list.add("只要看到他,你的坏脾气自然收敛起来,变得驯如羔羊。只要看到他,你的沮丧会消失得无影无踪。跟他一起,你才发现自己从没这么温柔过；跟他一起,你会努力表现得聪明些。——爱上了他,你有点怕他；爱上了他,你开始相信命运；是否前世你欠了他什么？谁知道,他治得了你。");
        list.add("一个小男孩拿着一张假钱走进了玩具店，准备买一架玩具飞机。 服务员阿姨说：“小朋友，你的钱不是真的。 ”小男孩反问道：“阿姨，难道你的飞机是真的？”");
        list.add("1、在眼圈周围涂上蜂蜜；2、用纱布蘸上酸奶，敷在眼睛周围；3、将鸡蛋煮熟后去壳，用小毛巾包裹住，按摩眼睛四周；4、将含汁量多的苹果切片，敷双眼；5、土豆去皮切成约2厘米的厚片，外敷双眼。");
        list.add("在你的U盘中建个空的文件夹，命名为autorun.inf。如果你的U盘无法完成重命名，这说明你的U盘已中毒，这时，那么建议你先备份重要文件，再格式化。原理是：大多数病毒是先建立autorun.inf再键入内容，病毒在进入C盘时就是通过这个文件夹里内部文件来为媒介的。");
        list.add("专家指出:往往上班玩游戏的员工,他的大脑要比专心工作的人聪明65%。比如某员工上班玩游戏的人,他的精力投在玩上,大脑的灵活率极度升高，而专心工作的员工没有一点灵活的空间,在社会上能干大事的人,据专家统计85%都没上过大学本科。");
        list.add("将手心朝下平放在桌面，然后五指张开，用另一手的指腹将拇指及食指下交接部位的肌肤用力夹起五秒钟（如下图），然后放开，若是马上皮肤缩回，表示你的生理年龄还很年轻，若超过5秒钟才完全恢复正常，代表你已经比实际年龄还要衰老了……");
        list.add("1.黑芝麻(增加细胞免疫) 2.西红柿(减少皮肤辐射损伤,祛斑)3.紫菜(抗辐射圣品) 4.辣椒(保护细胞的DNA不受辐射破坏)5.绿茶(减轻辐射对人体的不良影响) 6.海带(抗辐射) 7.大蒜(减少辐射损伤) 8.绿豆(有助于排泄体内毒物) 9.黑木耳(清胃、涤肠、防辐射)#小智慧#");
        list.add("按图示的动作，每天坚持重复20次，一星期减2公斤，不用控制食欲，立马给你漂亮身材。一开始的时候身上有点酸痛，坚持一段时间后，很快就适应了，大家一起加油吧！（全套10个动作 5/10）");
        list.add("夏天到了，你放松了对辛辣油腻食物的警惕，任由它们被超量摄入，影响体内循环代谢。再加上一年四季都摆脱不了的压力过大、作息不规律，积蓄在体内的各种“火气”——胃火、肝火、肺火、心火，就这样疯狂燃烧起来首先灼伤的，就是你的肌肤。#小智慧#");
        list.add("1. 草莓亮白牙齿；2. 牙膏治疗疱疹；3. 菠萝汁洁面； 4. 冰牛奶舒缓灼伤；5. 消毒药片洁甲；6. 柠檬汁恢复肤色；7. 橄榄油婴儿油卸唇妆；8. 阿司匹林去痘； 9. 柠檬汁护发；10. 粗盐去黑头； 11. 眼霜消丘疹");
        list.add("一、圆脸(对人际关系及财运都有加分)；二、下巴丰满（可能会拥有两栋以上的房产）；三、臀大(代表有财运)；四、腿不能细（腿长脚瘦，奔走不停，辛苦之相也）；五、小腹有脂肪(状是一种福寿之相)。(via @创意时尚空间）");
        list.add("《加勒比海盗：惊涛怪浪》：5月20日、《不再让你孤单》：5月20日(因为谐音\\'我爱你\\') 、《功夫熊猫2》：5月28日、《速度与激情5》：5月12日 、《雷神》《大太阳》《百年情书》：5月6日 、《最爱》《蔡李佛拳》：5月10日 、《男得有爱》5月13日 P.S你想看哪部？[色]");
        list.add("<p>在你的U盘中建个空的文件</p><p><img src=\"https://myvueblog-cjj.oss-cn-guangzhou.aliyuncs.com/vueblog/contentImg/2022030335da9903df4c4933bcd092d5279156dd1.jpeg\" width=\"213\" style=\"\"></p><p>即可洒不可解散</p><p>奥斯卡价格表是框架</p><p>奥科吉根本就上看j</p>");
        list.add("<p>皮肤辐射损伤</p><p><img src=\"https://myvueblog-cjj.oss-cn-guangzhou.aliyuncs.com/vueblog/contentImg/2022030350a3e10da6f44e65badf9a176ee96c1a2.jpg\" width=\"213\" style=\"\"></p><p>这是图片测试2</p><p>这是图片测试2</p><p>这是图片测试2j</p>");
        list.add("<p>响体内循环代</p><p><img src=\"https://myvueblog-cjj.oss-cn-guangzhou.aliyuncs.com/vueblog/contentImg/202203294e0c116ffa6542e28b5aa869a91bb6016.jpg\" width=\"213\" style=\"\"></p><p>安康给你hi哇u还包括加班费看啦</p><p>和我普及健康不好我回家</p><p>你们那叫哥哥</p>");
//        for (int i = 1; i < 172; i++) {
//            users.add(i);
//        }
        for (int i = 0; i <= 500000; i++) {
            int ran = (int)(Math.random() * list.size());
            int ranuser = (int) (Math.random() * 10191);
            blogContent.setContent(list.get(ran));
            blogContent.setDescription(list.get(ran).substring(0,20).trim());
            blogContent.setLook(i/3);
            blogContent.setPraise(i/4);
            blogContent.setTitle(list.get(ran).substring(10,25).trim());
            blogContent.setUserId(ranuser);
            blogContent.setUnpraise(i/130);
            String userName = blogUserMapper.getUserName(ranuser);
            blogContent.setUsername(userName);
            blogContentMapper.addOne(blogContent);

        }

    }


    @Test
    public void test2(){
        //20220328-20:01
        SimpleDateFormat format =  new SimpleDateFormat("yyyyMMdd-hh:mm");
        String date = format.format(new Date());
        BlogUser blogUser = new BlogUser();
        blogUser.setBuildTime(date);
        blogUser.setStatus(1);
        blogUser.setPassword("666");
        blogUser.setCreated(date);
        for (int i = 1; i < 10000; i++) {
            blogUser.setUsername("Cullen"+i);
            blogUser.setSumcomment(0);
            blogUser.setSumlook(i);
            blogUser.setSumpraise((int) (i*0.5));
            blogUser.setSumunpraise((int) (i*0.1));
            blogUser.setEmail("Cullen"+i + "@163.com");
            blogUserMapper.registerTest(blogUser);
        }
    }

    @Test
    public void test4(){
        String userName = blogUserMapper.getUserName(1);
        System.out.println(userName);
    }


}
