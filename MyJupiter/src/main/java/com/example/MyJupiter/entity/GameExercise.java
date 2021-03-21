package com.example.MyJupiter.entity;

// Builder builder = new Builder();
// builder.setName("as");
// builder.setDeveloper("cd");
// .. 外部进行 调用的时候
// GameExercise gameExercise = new GameExercise(builder);

// 用了 builder pattern， 就可以考虑 去掉 setter， 这样 builder build 出来之后， field 就不可以进行更改了
// 如果要达到 只读效果的化， 就不能给 每一个 private field 都来一个 setter, 维持 只读性
// update builder.setName() is different with 改 game 的 property， 因为是 new 了一个 object， 所以 改一个 builder object field
// 并不会影响 game object 的field

/**
 * builder pattern 的好处：
 * 提供参数不容易出错
 * 选择性提供不同参数
 *
 * */

public class GameExercise {
    private String name;
    private String developer;
    private String releaseTime;
    private String website;
    private double price;

    /**
     * builder pattern steps:
     * 1. field same as target class
     * 2. add setter
     * 3. add build() method
     * 4. game class update constructor
     * */

    /**
     * constructor should not have new itself, it could new other object
     * setter 是 public api， constructor field setting, use this.val = val or builder.val
     * */

    // how to do builder pattern
    // first have a builder object
    // builder object do setter
    // in builder class, need to have another function, that function will new Game object
    // pass this builder object into Game, set Game field

    /**
     * constructor public is good or private is good
     * good thing about public constructor:
     *   一个是可以直接class 这个 class 自己， 一个是通过 builder 来 call
     *      Game game = new Game(builder);
     *      Game game = builder.build();
     * */

    /**
     * 别人在使用的时候， 只有一条路， 可以创建 game
     * */
    //public GameExercise(GameBuilder builder) {
    private GameExercise(GameBuilder builder) {
        /**
         * constructor should not have new itself, it could new other object
         * setter 是 public api， constructor field setting, use this.val = val or builder.val
         * */

//        GameExercise game = new GameExercise(builder);
//        game.setDeveloper(builder.developer);

        this.name = builder.name;
        this.developer = builder.developer;
        this.releaseTime = builder.releaseTime;
        this.website = builder.website;
        this.price = builder.price;
    }

//    GameBuilder builder = new GameBuilder();
//        builder.setDeveloper("asd");
//        builder.setPrice(34.0);

//    public static class builder {
//        // could not be referenced by a static method
//        // once change both to be static, problem could be solved
////        GameBuilder gbuilder = new GameBuilder();
//
//        GameBuilder gameBuilder = GameExercise.GameBuilder;
//        gbuilder.setPrice(23.0);
//    }



    public String getName() {
        return name;
    }

//    public GameExercise setName(String name) {
//        this.name = name;
//        return this;
//    }

    public String getDeveloper() {
        return developer;
    }

//    public GameExercise setDeveloper(String developer) {
//        this.developer = developer;
//        return this;
//    }

    public String getReleaseTime() {
        return releaseTime;
    }

//    public GameExercise setReleaseTime(String releaseTime) {
//        this.releaseTime = releaseTime;
//        return this;
//    }

    public String getWebsite() {
        return website;
    }

//    public GameExercise setWebsite(String website) {
//        this.website = website;
//        return this;
//    }

    public double getPrice() {
        return price;
    }

//    public GameExercise setPrice(double price) {
//        this.price = price;
//        return this;
//    }

    // 错在 既然是 inner class， 是不应该有返回类型的
    // 出错的时候， 复述一下， 当前的情况
    // 我想 在 一个 inner class 里， 定义 private field
    // 首先检查， 我做的是不是符合 inner class 的定义， class 不要求 返回值
    // 其次 通过对比，来比较， 是不是 private 这里的定义导致出错

//    public static Game builder() {   // outside need to access this class field, without new it
//        // static Game builder() {   // same theory as public, since by default it is package level
//        // Game builder() {   // same theory as public, since by default it is package level
//                                // with static or without static, difference is 要不要实例化
//                                // 所以这里问题， 还是出在 access private field
//                                // inner Class private field setting
//        private String name;
//        private String developer;
//        private String releaseTime;
//        private String website;
//        private double price;
//
//    }

    public static class GameBuilder {
        // class 内部， 可以 . dot 出来
        private String name;
        private String developer;
        private String releaseTime;
        private String website;
        private double price;

        public GameBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public GameBuilder setDeveloper(String developer) {
            this.developer = developer;
            return this;
        }

        public GameBuilder setReleaseTime(String releaseTime) {
            this.releaseTime = releaseTime;
            // 除了 进行 set， 还返回了 builder 这个对象
            return this;
        }

        public GameBuilder setWebsite(String website) {
            this.website = website;
            return this;
        }

        public GameBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        // not constructor, 而是应该写成一个public方法， 这个方法在 target class 初始化 constructor 的时候，并不会被调用
        // target class 初始化 constructor， 通过 builder 的 field 来set
        // 并不是属于 class 的 default 属性
        // 这些 属性 应该是 caller 来进行 set， 而不是通过 constructor 来进行 set

//        public GameBuilder() {
//            GameBuilder gameBuilder = new GameBuilder();
//        }

        public GameExercise build() {
            return new GameExercise(this);
        }
    }


}
