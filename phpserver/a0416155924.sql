-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2015 年 03 月 04 日 06:24
-- 服务器版本: 5.0.96-community-nt
-- PHP 版本: 5.2.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `a0416155924`
--
CREATE DATABASE IF NOT EXISTS `a0416155924` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `a0416155924`;

-- --------------------------------------------------------

--
-- 表的结构 `adlist`
--

CREATE TABLE IF NOT EXISTS `adlist` (
  `id` int(11) NOT NULL auto_increment,
  `ids` int(11) default NULL,
  `name` varchar(255) default NULL,
  `img` varchar(1000) default NULL,
  `url` varchar(1000) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `adlist`
--

INSERT INTO `adlist` (`id`, `ids`, `name`, `img`, `url`) VALUES
(1, 2, '女友赢三张,玩的很漂亮', 'http://plugins.mrpcdn.com/down/62887e3f-9225-4cb4-a3c2-f182de4c00ae/653x279', 'http://www.maopaoke.com/open/app/591'),
(2, 3, '爱江山更爱美人！', 'http://plugins.mrpcdn.com/down/b4f9bc65-e0e3-49c6-9eba-f848bd8a35c3/653x279', 'http://www.maopaoke.com/open/app/2943'),
(3, 1, '北欧女神,屌丝逆袭', 'http://plugins.mrpcdn.com/down/ccb1a35d-7130-4c0c-94b4-7489919954f8/653x279', 'http://www.maopaoke.com/open/app/2769');

-- --------------------------------------------------------

--
-- 表的结构 `apps`
--

CREATE TABLE IF NOT EXISTS `apps` (
  `ids` int(11) NOT NULL auto_increment,
  `id` int(11) NOT NULL,
  `icon` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `des` varchar(1000) default NULL,
  `orderNum` int(11) default NULL,
  `pkg` varchar(255) default NULL,
  `type` varchar(255) default NULL,
  `oId` varchar(255) default NULL,
  `url` varchar(1000) default NULL,
  `size` varchar(255) default NULL,
  `fl` varchar(255) default NULL,
  `rjxj` varchar(255) default NULL,
  `bb` varchar(255) default NULL,
  PRIMARY KEY  (`ids`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

--
-- 转存表中的数据 `apps`
--

INSERT INTO `apps` (`ids`, `id`, `icon`, `title`, `des`, `orderNum`, `pkg`, `type`, `oId`, `url`, `size`, `fl`, `rjxj`, `bb`) VALUES
(1, 1001, 'http://plugins.mrpcdn.com/down/2b66a3c8-05e9-4b09-8416-fcfc70f2aa91/100x100', '消灭星星2（有奖版）', '消灭星星2（有奖版）是一款简单好玩的休闲游戏，玩法虽然简单，要想得高分还需动动脑子，没有时间限制，有充足的时间考虑哦。 玩法：只要点两个或两个以上的星星就可以消除得分，连接的星星越多，得分越高。 请尽情体验吧！', 5, 'com.maopaoke.mpk6002419.sms', '2', '1399343778', 'http://plugins.mrpcdn.com/down/b29cd88f-6a37-49bf-9889-36704bb69c01/%E6%B6%88%E7%81%AD%E6%98%9F%E6%98%9F2%EF%BC%88%E6%9C%89%E5%A5%96%E7%89%88%EF%BC%89_1.1.7_%E7%BA%BF%E6%9D%A1%E3%80%8E%E7%BB%9D%E7%89%88%E7%94%B7%E7%B1%BD%E3%80%8F%E2%86%93%E4%BE%91%E7%82%B9%E6%BA%85_%E6%88%91%E7%9A%84%E6%94%B6%E5%85%A5.apk?channelId=172340&verId=6651&appId=6002419&mpkType=1&mpkTemplateVersion=113&sdkkey=0&s=7b35683c7a501c446c2164b94777b5a9', '3.6M', '创意休闲', '5', '1.1.7'),
(2, 1001, 'http://plugins.mrpcdn.com/down/054d5886-5012-4b8a-9b42-d41fc2eb799a/100x100', '新火影忍者', '在这里，你将遇到最熟悉的朋友或敌人，鸣人、大蛇丸、鼬、团藏……！ 在这里，你将拥有草雉剑、千鸟剑、十拳剑等上古神器！ 在这里，你将学到豪火球术、龙火之术、写轮眼天照等酷炫技能！ 在这里，你甚至可以学到火影之最强瞳术：须佐能乎！ 探寻家族灭门惨案，完成人生复仇大业，你还在等什么！', 2, 'com.maopaoke.mpk6002353.sms', '2', '1412657368', 'http://plugins.mrpcdn.com/down/fc89abd9-08ad-4aed-9a0e-2a6f40c0fa68/%E6%96%B0%E7%81%AB%E5%BD%B1%E5%BF%8D%E8%80%85_1.0.03_%E7%BA%BF%E6%9D%A1%E3%80%8E%E7%BB%9D%E7%89%88%E7%94%B7%E7%B1%BD%E3%80%8F%E2%86%93%E4%BE%91%E7%82%B9%E6%BA%85_%E4%BD%BF%E7%94%A8%E4%BA%BA%E5%91%98%E7%BB%9F%E8%AE%A1.apk?channelId=172340&verId=6540&appId=6002353&mpkType=1&mpkTemplateVersion=113&sdkkey=0&s=38ad84a57587eb5009133ad8b2ee3268', '11.5M', '角色扮演', '3.5', '1.0.03'),
(3, 1001, 'http://plugins.mrpcdn.com/down/0566a9f1-4ae0-4fd0-8a0e-8a7c00c8a454/100x100', '雷电2014雷霆百度版', '雷电系列游戏2014年正统官方大作，最高品质热血空战街机游戏重磅出击！多国游戏排行榜第一的品质绝对物超所值！简单的操作，超爽快的手感，抛开一切繁琐，轻松体验顶级科幻战机的飞行乐趣！超炫的弹幕，激昂的音乐，带来视觉与听觉的双重盛宴！', 4, 'com.maopaoke.mpk6002833.sms', '2', '1399343833', 'http://plugins.mrpcdn.com/down/9bfc050d-a8fe-48cb-8c84-4e64442388b2/%E9%9B%B7%E7%94%B52014%E9%9B%B7%E9%9C%86%E7%99%BE%E5%BA%A6%E7%89%88_2.8_%E7%BA%BF%E6%9D%A1%E3%80%8E%E7%BB%9D%E7%89%88%E7%94%B7%E7%B1%BD%E3%80%8F%E2%86%93%E4%BE%91%E7%82%B9%E6%BA%85_%E4%BD%BF%E7%94%A8%E4%BA%BA%E5%91%98%E7%BB%9F%E8%AE%A1.apk?channelId=172340&verId=8047&appId=6002833&mpkType=1&mpkTemplateVersion=113&sdkkey=0&s=de45a4c0631c0453fa55eb6f82853488', '9.7M', '动作射击 ', '5', '2.8'),
(4, 1001, 'http://plugins.mrpcdn.com/down/9bfb84cc-7fc5-43f3-8caa-491995cee4f7/100x100', '火拼斗地主', '【唯一每天都有奖的斗地主】 《火拼斗地主》百万在线人气，天天送金币，每月有巨奖！注册永久免费玩，最省流量的斗地主!【游戏特色】 1、速度超快！特有10秒必开局系统给您超爽的感觉。2、专业防作弊，动态速配等方式让比赛公平公正！3、流量超省！斗一局流量少于看一个wap新闻。4、凤姐、凹巴马、葫芦娃搞笑卡通随时惊喜炸弹砸到你！', 3, 'com.maopaoke.mpk6001473', '2', '1412657271', 'http://plugins.mrpcdn.com/down/2b27d779-6d18-48e9-b03e-5637b2c5481f/%E7%81%AB%E6%8B%BC%E6%96%97%E5%9C%B0%E4%B8%BB_1.0.16.2_%E7%BA%BF%E6%9D%A1%E3%80%8E%E7%BB%9D%E7%89%88%E7%94%B7%E7%B1%BD%E3%80%8F%E2%86%93%E4%BE%91%E7%82%B9%E6%BA%85_%E4%BD%BF%E7%94%A8%E4%BA%BA%E5%91%98%E7%BB%9F%E8%AE%A1.apk?channelId=172340&verId=4890&appId=6001473&mpkType=1&mpkTemplateVersion=3&sdkkey=0&s=0d724428d3cc97b0990690c242ea98bf', '5.3M', '益智棋牌', '4', '1.0.16.2'),
(5, 1001, 'http://plugins.mrpcdn.com/down/0140c9c1-ff07-4199-8f4d-2ba3af8311a9/100x100', '天天赛车', '当你越过一条又一条华丽精美的赛道，超越一个又一个玩家时，你心中是否享受着曼妙的快感呢？小桥流水，沼泽，神秘的岩洞，危机四伏的陨石，陡峭险峻的山跛，一旦开始，你根本就停不下来！心跳加速、紧张气氛，时刻充斥着你的神经……你敢挑战吗？！hold on～ 新玩法：这不仅仅是极速飞奔就可以，你能否越过前面那道坎经得起考验？炫出你的车技吧！ 简单：只需通过“左右”操作即可在赛道上享受超越对手的快感！ 心跳：五大赛道，险峻惊魂，时刻挑动你的神经！！！ 比拼：全国赛车友，火热排行，飞奔夺冠，赶快登上富豪榜吧！', 1, 'com.maopaoke.mpk6003198.sms', '2', '1412657492', 'http://plugins.mrpcdn.com/down/49e500de-c718-4910-8dc3-bc0281d4ce73/%E5%A4%A9%E5%A4%A9%E8%B5%9B%E8%BD%A6_1.1.5_%E7%BA%BF%E6%9D%A1%E3%80%8E%E7%BB%9D%E7%89%88%E7%94%B7%E7%B1%BD%E3%80%8F%E2%86%93%E4%BE%91%E7%82%B9%E6%BA%85_%E4%BD%BF%E7%94%A8%E4%BA%BA%E5%91%98%E7%BB%9F%E8%AE%A1.apk?channelId=172340&verId=8324&appId=6003198&mpkType=1&mpkTemplateVersion=113&sdkkey=0&s=c30b3d4827255d97adab2ade62c225bc', '9.2M', '体育竞速', '3.5', '1.1.5'),
(6, 1001, 'http://plugins.mrpcdn.com/down/bc5993fc-52e6-44d4-b2c9-895f9979f996/100x100', '女友赢三张', '女优赢三张是一款真人联网对战的赢三张游戏。实现联网真人对战模式，更有结婚系统让屌丝在游戏中成功逆袭高富帅！更具刺激性，汇聚各地玩家，高手云集，挑战你无限智慧与运气的极限。融入大型家装玩法，引导团队作战。', 6, 'com.skymobi.nvyouzhajinhua', '2', '1413291556', 'http://plugins.mrpcdn.com/down/36ddfdd8-2ea2-4d8b-83bc-365ead1b6029/%E5%A5%B3%E5%8F%8B%E8%B5%A2%E4%B8%89%E5%BC%A0_28.000_%E7%BA%BF%E6%9D%A1%E3%80%8E%E7%BB%9D%E7%89%88%E7%94%B7%E7%B1%BD%E3%80%8F%E2%86%93%E4%BE%91%E7%82%B9%E6%BA%85_%E4%BD%BF%E7%94%A8%E4%BA%BA%E5%91%98%E7%BB%9F%E8%AE%A1.apk?channelId=172340&verId=7905&appId=6000502&s=96fbbff3986bc8a2067d18589b91855c', '9.9M', '卡牌益智', '5', '28.000'),
(11, 1001, 'http://plugins.mrpcdn.com/down/bcb39a9f-f553-428f-8725-8c54dd817b28/100x100', '糖果消消', '糖果消消是一个很锻炼脑力的游戏和传统的消除类游戏不同，本游戏没有时间限制，只要两个相同颜色的糖果就可以消除，以关卡的形式进行游戏，每个关卡的分数需求累计增加。', 11, 'com.maopaoke.mpk6003147.sms', '2', '1421835281', 'http://plugins.mrpcdn.com/down/53b45726-4bcc-480e-b549-bcb423c46a56/%E7%B3%96%E6%9E%9C%E6%B6%88%E6%B6%88_2.3_%E7%BA%BF%E6%9D%A1%E3%80%8E%E7%BB%9D%E7%89%88%E7%94%B7%E7%B1%BD%E3%80%8F%E2%86%93%E4%BE%91%E7%82%B9%E6%BA%85_%E6%88%91%E7%9A%84%E6%94%B6%E5%85%A5.apk?channelId=172340&verId=7913&appId=6003147&mpkType=1&mpkTemplateVersion=113&sdkkey=0&s=d950cf62c015f34b15b1ef58d957180c', '7.4M', '创意休闲', '5', '2.3'),
(7, 1001, 'http://plugins.mrpcdn.com/down/a5b21978-510b-4cca-8e71-3e099af57844/100x100', '无尽之战', '游戏特色： #同类物种的进化，获得更强大的力量和不可思议的新物种。 #丰富的物种合成，发挥你的想象力来创造新物种。 #多通道塔防，出色得运用策略来赢得战斗。 #高质量华丽的游戏画面配合惊心动魄的音效。 #充满挑战的BOSS关卡，闯关更极限！', 7, 'com.maopaoke.mpk6002021.sms', '2', '1415456240', 'http://plugins.mrpcdn.com/down/00da43e1-83fd-4d94-b7a7-ff0d6009afdf/%E6%97%A0%E5%B0%BD%E4%B9%8B%E6%88%98_1.0.9_%E7%BA%BF%E6%9D%A1%E3%80%8E%E7%BB%9D%E7%89%88%E7%94%B7%E7%B1%BD%E3%80%8F%E2%86%93%E4%BE%91%E7%82%B9%E6%BA%85_%E6%88%91%E7%9A%84%E6%94%B6%E5%85%A5.apk?channelId=172340&verId=6522&appId=6002021&mpkType=1&mpkTemplateVersion=113&sdkkey=0&s=261ca7ecf79fb1799139ae882c3d3085', '13.6M', '塔防策略', '5', '1.0.9'),
(8, 1001, 'http://plugins.mrpcdn.com/down/42fa9305-cf6e-440b-b3d7-ba2967ab8fad/100x100', '狂斩三国', '画风独特：三国名将，拉风威武。不装Q、不卖萌； 超爽快感：划屏狂斩，创意玩法、酣畅淋漓； 碉堡技能：丰富技能、华丽特效。无双技能闪耀全屏； 装备系统：收集神器、化身长坂英雄，万人军中如入无人之地； 炫酷装备、尽收囊中！ 玩法丰富：追杀、破城、护送、坚守……', 8, 'com.maopaoke.mpk6001821', '2', '1415456383', 'http://plugins.mrpcdn.com/down/1dbbd0ae-dd2d-4d59-962f-5b6698af306e/%E7%8B%82%E6%96%A9%E4%B8%89%E5%9B%BD%E5%A4%9A%E9%85%B7%E7%89%88_2.1.2_%E7%BA%BF%E6%9D%A1%E3%80%8E%E7%BB%9D%E7%89%88%E7%94%B7%E7%B1%BD%E3%80%8F%E2%86%93%E4%BE%91%E7%82%B9%E6%BA%85_%E6%88%91%E7%9A%84%E6%94%B6%E5%85%A5.apk?channelId=172340&verId=5622&appId=6001821&mpkType=1&mpkTemplateVersion=4&sdkkey=0&s=606ab35f0765f7b50e8b25632c5c43df', '14.2M', '角色扮演', '4.5', '2.1.2'),
(9, 1001, 'http://plugins.mrpcdn.com/down/ce18fce7-2dbb-4f04-9a78-99897080e29d/100x100', '部落大消除', '以《部落反斗蛙》动漫故事为背景的消除游戏《部落大消除》来了。 100个游戏关卡, 冲破铁笼、藤蔓和冰封的阻挡挑战身怀绝技的部落守卫, 更有4种法宝（雷霆锤、天空枪、铉铁钩、五彩石）助你所向披靡。 部落的大门正式打开，与你的朋友一起开始一段惊险刺激的部落之旅吧！ 玩法 在游戏中，只需点击两个或两个以上相邻且颜色相同普通色块的其中一个，即可将这些色块消除。除此之外，还有更多种类的色块和道具。 消除真的很简单.', 10, 'com.maopaoke.mpk6002248.sms', '2', '1415456629', 'http://plugins.mrpcdn.com/down/ed453a67-8b82-4ae2-bb21-a4fdb3fc6ce6/%E9%83%A8%E8%90%BD%E5%A4%A7%E6%B6%88%E9%99%A4_2.0_%E7%BA%BF%E6%9D%A1%E3%80%8E%E7%BB%9D%E7%89%88%E7%94%B7%E7%B1%BD%E3%80%8F%E2%86%93%E4%BE%91%E7%82%B9%E6%BA%85_%E6%88%91%E7%9A%84%E6%94%B6%E5%85%A5.apk?channelId=172340&verId=6396&appId=6002248&mpkType=1&mpkTemplateVersion=108&sdkkey=0&s=f93b4dc506d3c376e0cc04adc50f1d37', '8.3M', '创意休闲', '4', '2.0'),
(10, 1001, 'http://plugins.mrpcdn.com/down/b13dfe36-b4c4-4004-b79b-5592688dcebf/100x100', '脱你妹', '性感美女，脱衣的诱惑，赤裸裸的勾引！快感游戏尽在《脱你妹》！', 11, 'com.maopaoke.mpk6002108.sms', '2', '1415456808', 'http://plugins.mrpcdn.com/down/51fee3e9-02ee-4920-b8e9-a4d9191f199e/%E8%84%B1%E4%BD%A0%E5%A6%B9--%E5%86%92%E6%B3%A1_2.0_%E7%BA%BF%E6%9D%A1%E3%80%8E%E7%BB%9D%E7%89%88%E7%94%B7%E7%B1%BD%E3%80%8F%E2%86%93%E4%BE%91%E7%82%B9%E6%BA%85_%E6%88%91%E7%9A%84%E6%94%B6%E5%85%A5.apk?channelId=172340&verId=6598&appId=6002108&mpkType=1&mpkTemplateVersion=113&sdkkey=0&s=ae09b4bef1a6ba867a6bad44c09eea4b', '5.5M', '创意休闲', '4', '2.0');

-- --------------------------------------------------------

--
-- 表的结构 `ct_ad`
--

CREATE TABLE IF NOT EXISTS `ct_ad` (
  `id` int(11) NOT NULL auto_increment,
  `imgurl` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `sm` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `ct_ad`
--

INSERT INTO `ct_ad` (`id`, `imgurl`, `url`, `sm`) VALUES
(2, 'http://www.mrpoid.com/admin/uploads/20150225150017.jpg', 'http://m.baidu.com/static/index/plus/plus_logo.png', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `ct_member`
--

CREATE TABLE IF NOT EXISTS `ct_member` (
  `uid` int(11) NOT NULL auto_increment,
  `head` varchar(255) default NULL,
  `name` varchar(255) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `nickname` varchar(255) default NULL,
  `sign` varchar(255) default NULL,
  `sex` varchar(255) default NULL,
  `age` int(11) default NULL,
  `phone` bigint(20) default NULL,
  `address` varchar(255) default NULL,
  `status` int(11) default '1',
  `pushuid` varchar(255) default NULL,
  `puchcid` varchar(255) default NULL,
  `pushtag` varchar(255) default NULL,
  PRIMARY KEY  (`uid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `ct_member`
--

INSERT INTO `ct_member` (`uid`, `head`, `name`, `pwd`, `nickname`, `sign`, `sex`, `age`, `phone`, `address`, `status`, `pushuid`, `puchcid`, `pushtag`) VALUES
(1, 'http://www.mrpoid.com/admin/uploads/1_20150304142435.jpg', '000000', 'abcffb5cd2389d9cab005d771d19b82a', '管理员', '这家伙很懒,什么也没留下！', '男', 101, 13798834657, '暂无地址', 0, NULL, NULL, NULL),
(2, 'http://www.mrpoid.com/admin/uploads/20150301040857.jpg', 'admin', 'abcffb5cd2389d9cab005d771d19b82a', '管理员', '测试啦', '男', 25, 13798834657, '重庆市', 1, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `ct_notice`
--

CREATE TABLE IF NOT EXISTS `ct_notice` (
  `id` int(11) NOT NULL auto_increment,
  `text` text,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `ct_notice`
--

INSERT INTO `ct_notice` (`id`, `text`) VALUES
(1, '测试啦\r\n');

-- --------------------------------------------------------

--
-- 表的结构 `mes`
--

CREATE TABLE IF NOT EXISTS `mes` (
  `id` int(11) NOT NULL auto_increment,
  `mes` varchar(1000) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `mes`
--

INSERT INTO `mes` (`id`, `mes`) VALUES
(1, '由于此应用涉及到某公司的版权倾述，作者已放弃继续开发下去了。得到此软件的用户请不要外传该应用程序，以免造成不必要的麻烦。谢谢配合！');

-- --------------------------------------------------------

--
-- 表的结构 `yh`
--

CREATE TABLE IF NOT EXISTS `yh` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `pwd` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `yh`
--

INSERT INTO `yh` (`id`, `name`, `pwd`) VALUES
(1, 'admin', 'abcffb5cd2389d9cab005d771d19b82a');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
