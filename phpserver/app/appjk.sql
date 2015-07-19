
--
-- 数据库: `appjk`
--

-- --------------------------------------------------------

--
-- 表的结构 `adlist`
--

CREATE TABLE IF NOT EXISTS `adlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ids` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `img` varchar(1000) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `adlist`
--

INSERT INTO `adlist` (`id`, `ids`, `name`, `img`, `url`) VALUES
(1, 6, 'admin', 'http://plugins.mrpcdn.com/down/aef0d987-0c68-45d9-83a9-ee78c6d981f3/653x279', 'http://www.apkmopo.com'),
(2, 7, 'admin', 'http://plugins.mrpcdn.com/down/ba16bfa5-f169-4ccd-9107-c35557686916/653x279', 'http://www.maopaoke.com/open'),
(3, 1, '冒泡', 'http://plugins.mrpcdn.com/down/193a9605-483d-4ce0-b6e8-4ab1d33bf3cb/653x279', 'http://www.apkmopo.com');

-- --------------------------------------------------------

--
-- 表的结构 `apps`
--

CREATE TABLE IF NOT EXISTS `apps` (
  `ids` int(11) NOT NULL AUTO_INCREMENT,
  `id` int(11) NOT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `des` varchar(1000) DEFAULT NULL,
  `orderNum` int(11) DEFAULT NULL,
  `pkg` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `oId` varchar(255) DEFAULT NULL,
  `url` varchar(1000) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `fl` varchar(255) DEFAULT NULL,
  `rjxj` varchar(255) DEFAULT NULL,
  `bb` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ids`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=18 ;

--
-- 转存表中的数据 `apps`
--

INSERT INTO `apps` (`ids`, `id`, `icon`, `title`, `des`, `orderNum`, `pkg`, `type`, `oId`, `url`, `size`, `fl`, `rjxj`, `bb`) VALUES
(5, 1001, 'http://plugins.mrpcdn.com/down/0d25a194-f013-4146-b3e4-d78c52bad49d/100x100', '猎艳仙境', '极富挑战的战斗玩法，装备、宠物、仙园，美女，样样都充满神奇。游戏中，还能拉朋唤友，结成帮会，结识朋友，一起体验游戏的乐趣。 作为一款手机网游，游戏对移动端操作进行了深度的优化，让你玩起来既轻松又享受，同时即时战斗语音功能将令你可以直接与战友对话，甩开手机打字的繁琐。 精美的画面，壮丽的场景，无论是随身小巧的手机还是震撼爽快的大平板，游戏过程中都能欣赏到美轮美奂的神界仙境。 游戏特色： 1、真正的手动操作回合制，真正的游戏战斗乐趣。 2、五种性格鲜明、各自身怀绝技的职业，可爱精灵的随身仙宠，伴你一路成长的坐骑，炫丽神奇的多种等级装备，你需要开动大脑，拿出智慧，才能让自己无人能敌。 3、战斗语音功能，令你可以与队友即时对话，充分发挥团队作战的配合优势。 4、丰富的关卡，极富挑战无尽试炼，丰富的PVE让你挖掘你的战斗潜力。 5、紧张刺激的竞技场和帮战，个人和帮会的荣誉在此奠定。 6、休闲而有趣的仙境温泉，让你在游戏之余获得闲适，还可用亲一亲、摸一摸捉弄好友，一起获得经验提升。																																								', 1000, '猎艳仙境', '2', '1399343778', 'http://plugins.mrpcdn.com/down/eff69d77-07fa-4f7b-81b4-f7d88b29dd0c/%E7%8C%8E%E8%89%B3%E4%BB%99%E5%A2%83_2.1.7.0_%E7%BA%BF%E6%9D%A1%E3%80%8E%E7%BB%9D%E7%89%88%E7%94%B7%E7%B1%BD%E3%80%8F%E2%86%93%E4%BE%91%E7%82%B9%E6%BA%85_%E4%BD%BF%E7%94%A8%E4%BA%BA%E5%91%98%E7%BB%9F%E8%AE%A1.apk?channelId=172340&verId=6064&appId=6002106&mpkType=1&mpkTemplateVersion=7&sdkkey=0&s=0e4a26bb4eea5e2c7064c151a8d27755', '20M', '1', '5', '1.0'),
(6, 1001, 'http://plugins.mrpcdn.com/down/823af53c-2043-4a59-9953-28035e14ec56/100x100', '大闹天宫（HD）', '齐天大圣孙悟空勇闯南天门，玩家需要控制孙悟空来闯过天庭设置的各种阻碍物。玩法紧张、刺激、新奇。欢迎您来挑战。				', 111, '大闹天宫（HD）', '2', '1399343833', 'http://plugins.mrpcdn.com/down/b7204f13-c5e4-47ed-909a-abadff5bc411/%E5%A4%A7%E9%97%B9%E5%A4%A9%E5%AE%AB%EF%BC%88HD%EF%BC%89_1.1_%E7%BA%BF%E6%9D%A1%E3%80%8E%E7%BB%9D%E7%89%88%E7%94%B7%E7%B1%BD%E3%80%8F%E2%86%93%E4%BE%91%E7%82%B9%E6%BA%85_%E4%BD%BF%E7%94%A8%E4%BA%BA%E5%91%98%E7%BB%9F%E8%AE%A1.apk?channelId=172340&verId=6331&appId=6002204&mpkType=1&mpkTemplateVersion=108&sdkkey=0&s=ceba01c399cbd117e7a8f619cb6412a6', '3.6M', '1', '10', '2.0'),
(16, 3001, 'http://zhushou.apkmopo.com/zhushou/maopao/mrpwy.png', '冒泡网游', '目前世界在线第一的手机网游，由您参与以后必然更精彩！结婚系统，副将系统神兵利器，战争活动，幻想三国比肩嗲给您最满意的游戏体验！', 300, 'hxsg.mrp', '1', '1399462961', 'http://f.mrpcdn.com/dev.php?fid=default--fc15ae7b29128b6718d3587941eeec90', '10', '1', '10', '10'),
(17, 3001, 'http://zhushou.apkmopo.com/zhushou/maopao/mrpcc.png', '冒泡棋牌', '全球最火爆手机真人斗地主900万美眉帅哥陪你一起玩！手机、话费、神秘巨奖等你赢！美眉24小时在线聊天打牌，共享无限激情！还等什么，赶快加入！', 20, 'mrpcc.mrp', '1', '1399463088', 'http://f.mrpcdn.com/dev.php?fid=default--b3c6a6f4c4f0b77903ee10709b353e9d', '1', '1', '1', '21');

-- --------------------------------------------------------

--
-- 表的结构 `mes`
--

CREATE TABLE IF NOT EXISTS `mes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mes` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `mes`
--

INSERT INTO `mes` (`id`, `mes`) VALUES
(1, '');

-- --------------------------------------------------------

--
-- 表的结构 `yh`
--

CREATE TABLE IF NOT EXISTS `yh` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `yh`
--

INSERT INTO `yh` (`id`, `name`, `pwd`) VALUES
(1, 'admin', '14e1b600b1fd579f47433b88e8d85291');
