package com.haixiaolu.stream;

import lombok.val;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {
        // 查看基本信息

//        List<Author> authors = getAuthors();
//        System.out.println(authors);

//        打印所有年龄小于18的作家的名字， 并且要注意去重

        // lambda 写法
        List<Author> authors = getAuthors();
        authors.stream() // 把集合转换成流
                .distinct() // 先去除重复的作家
                .filter(author -> author.getAge() < 18) // 筛选年龄小于18的
                .forEach(author -> System.out.println(author.getName())); // 遍历打印名字

        // 第一种写法
//        List<Author> authors1 = getAuthors();
//        authors1.stream()
//                .distinct()
//                .filter(new Predicate<Author>() {
//                    @Override
//                    public boolean test(Author author) {
//                        return author.getAge() < 18;
//                    }
//                })
//                .forEach(new Consumer<Author>() {
//                    @Override
//                    public void accept(Author author) {
//                        System.out.println(author.getName());
//                    }
//                });

        // 打印所有姓名长度大于1的作家的姓名
        // filter
        authors.stream()
                .filter(author ->author.getName().length() > 1)
                .forEach(author -> System.out.println(author.getName()));
        // map
        // 打印所有作家的名字
        authors.stream()
                .map(author -> author.getName())
                .forEach(name -> System.out.println(name));

        authors.stream()
                .map(author -> author.getAge())
                .map(age -> age + 10)
                .forEach(age -> System.out.println(age));

        // 对流中的元素按照年龄进行降序排序， 并且要求不能有重复的元素
        authors.stream()
                .distinct()
                .sorted(new Comparator<Author>() {
                    @Override
                    public int compare(Author o1, Author o2) {
                        return o2.getAge() - o1.getAge();
                    }
                })
                .forEach(author -> System.out.println(author.getAge()));


        // limit
        // 需求：对流中的元素按照年龄进行降序排序， 并且要求不能有重复的元素
        authors.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .limit(2)
                .forEach(author -> System.out.println("author: " + author.getName()));

        // skip
        // 打印除了年龄最大的作家外的其他作家， 要求不能重复元素， 并且按照年龄降序排序
        authors.stream()
                .distinct()
                .sorted(((o1, o2) -> o2.getAge() - o1.getAge()))
                .skip(2)
                .forEach(author -> System.out.println(author.getAge()));

        // flatMap
        // 打印所有书籍的名字， 要求对重复的元素进行去重
        // 匿名类实现法
        authors.stream()
                .flatMap(new Function<Author, Stream<Book>>() {
                    @Override
                    public Stream<Book> apply(Author author) {
                        return author.getBooks().stream();
                    }
                })
                .distinct()
                .forEach(new Consumer<Book>() {
                    @Override
                    public void accept(Book book) {
                        System.out.println(book.getName());
                    }
                });

        // lambda
        authors.stream()
                .flatMap((Function<Author, Stream<Book>>) author -> author.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));

        // 打印现有数据的所有分类， 要求对分类进行去重， 不能出现这种格式： 哲学，爱情

        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                .distinct()
                .forEach(category -> System.out.println(category));

        /**
         * 终结操作
         */
        // forEach 的用法： 输出所有作家的名字
        authors.stream()
                .map(author -> author.getName())
                .distinct()
                .forEach(name -> System.out.println(name));

        // 2. count: 打印这些作家的所出书籍的数目， 删除重复元素
        long count1 = authors.stream()
                .map(author -> author.getBooks().stream())// 书籍
                .distinct()
                .count();
        System.out.println(count1);

        // 分别获取这些作家的所出书籍的最高分和最低分的并打印
        // Stream<Author> -> Stream<Book> -> Stream<Integer>
        Optional<Integer> max = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .max((o1, o2) -> o1 - o2);
        System.out.println(max.get());

        Optional<Integer> min = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .min((o1, o2) -> o1 - o2);
        System.out.println(min.get());

        // 获取一个存放所有作者名字的List集合
        List<String> list = authors.stream()
                .map(author -> author.getName())
                .collect(Collectors.toList());

        System.out.println(list);

        // 获取一个所有书名的Set集合
        Set<Book> books = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .collect(Collectors.toSet());
        System.out.println(books);

        // 获取一个Map集合， map的key为作者名， value是List<Book>
//        Map<String, List<Book>> listMap = authors.stream()
//                .distinct()
//                .collect(Collectors.toMap(author -> author.getName(), author -> author.getBooks()));

//        判断是否有年龄在29岁以上的作家
        boolean match = authors.stream()
                .anyMatch(author -> author.getAge() > 29);
        System.out.println(match);

        // 判断是否所有作家都是成年人
        boolean b = authors.stream()
                .allMatch(author -> author.getAge() >= 18);
        System.out.println(b);

        // 判断作家是否都没有超过100岁的
        boolean b1 = authors.stream().noneMatch(author -> author.getAge() > 100);
        System.out.println(b1);

        // 获取任意一个年龄大于18的作家， 如果存在就输出他的名字
        Optional<Author> optionalAuthor = authors.stream()
                .filter(author -> author.getAge() > 18)
                .findAny();
        optionalAuthor.ifPresent(author -> System.out.println(author.getName()));

        // *获取一个年龄最小的作家， 并输出他的名字*
        Optional<Author> first = authors.stream()
                .sorted((o1, o2) -> o1.getAge() - o1.getAge())
                .findFirst();
        first.ifPresent(author -> System.out.println(author.getName()));

        // reduce: *使用reduce求所有作者年龄的和*
        Integer sum = authors.stream()
                .map(author -> author.getAge()) // 把流转换成integer
                .reduce(0, (result, element) -> result + element);
        System.out.println(sum);

        // 使用reduce求所有作者中年龄的最大值
        authors.stream()
                .map(author -> author.getAge()) // 把流转换成integer
                .reduce(Integer.MIN_VALUE, (result, element) -> result < element ? element : result);

        // 使用reduce求所有作者中年龄的最小值
        authors.stream()
                .map(author -> author.getAge()) // 把流转换成integer
                .reduce(Integer.MAX_VALUE, (result, element) -> result > element ? element : result);
    }

    private static List<Author> getAuthors(){
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟这里的人", null);
        Author author2 = new Author(2L, "亚拉索", 15, "狂风也追逐不上他的思考速度", null);
        Author author3 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);
        Author author4 = new Author(3L, "易", 14, "是这个时间在限制他的思维", null);

        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L, "刀的两侧是光明与黑暗", "哲学， 爱情", 88, "用一把刀划分了爱情"));
        books1.add(new Book(2L, "一个人不能死在同一把刀下", "个人成长， 爱情", 89, "讲述如何从失败中明悟真理"));

        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的风采"));
        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的风采"));
        books2.add(new Book(4L, "吹或不吹", "爱情， 个人传记", 56, "一个哲学家的恋爱观"));

        books3.add(new Book(5L, "你的剑就是我的剑", "爱情", 56, "无法想象一个武者"));
        books3.add(new Book(6L, "风与剑， 个人传记", "爱情， 个人传记", 100, "两个哲学家灵魂和肉体"));
        books3.add(new Book(6L, "风与剑， 个人传记", "爱情， 个人传记", 100, "两个哲学家灵魂和肉体"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author, author2, author3, author4));
        return authorList;





    }
}
