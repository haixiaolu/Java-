package com.haixiaolu.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamDemo {

    public static void main(String[] args) {
        List<Author> authors = getAuthors();
        System.out.println(authors);

    }

    private static List<Author> getAuthors(){
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟这里的人", null);
        Author author2 = new Author(2L, "亚拉索", 15, "狂风也追逐不上他的思考速度", null);
        Author author3 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);
        Author author4 = new Author(4L, "易", 14, "是这个时间在限制他的思维", null);

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
