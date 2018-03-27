package com.bca.utool.jtable.po;

import org.apache.commons.lang.builder.ToStringBuilder;

/** @author: 北京华夏翰科科技有限公司-工具软件部.
 *           http://www.hxhk.com.cn
 */
/** 持久类定义
 *  类：com.bca.utool.jtable.po.HkBook
 *  映射到表：test01.HK_BOOK
 * 创建时间：2012-09-01 13:37:03.156
 */
public class HkBook implements java.io.Serializable {

    private int bookId;		// field BOOK_ID : NUMBER(22)
    private java.lang.String bookName;		// field BOOK_NAME : VARCHAR2(50)
    private java.lang.String bookAuthor;		// field BOOK_AUTHOR : VARCHAR2(20)
    private java.sql.Timestamp bookDate;		// field BOOK_DATE : DATE(7)
    private java.lang.String bookPublish;		// field BOOK_PUBLISH : VARCHAR2(50)

    public HkBook() {
    }

    public HkBook(int bookId) {
        this.bookId = bookId;
    }

    public HkBook(int bookId, java.lang.String bookName, java.lang.String bookAuthor, java.sql.Timestamp bookDate, java.lang.String bookPublish) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookDate = bookDate;
        this.bookPublish = bookPublish;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookId() {
        return this.bookId;
    }

    public void setBookName(java.lang.String bookName) {
        this.bookName = bookName;
    }

    public java.lang.String getBookName() {
        return this.bookName;
    }

    public void setBookAuthor(java.lang.String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public java.lang.String getBookAuthor() {
        return this.bookAuthor;
    }

    public void setBookDate(java.sql.Timestamp bookDate) {
        this.bookDate = bookDate;
    }

    public java.sql.Timestamp getBookDate() {
        return this.bookDate;
    }

    public void setBookPublish(java.lang.String bookPublish) {
        this.bookPublish = bookPublish;
    }

    public java.lang.String getBookPublish() {
        return this.bookPublish;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("bookId", bookId).append("bookName", bookName).append("bookAuthor", bookAuthor).append("bookDate", bookDate).append("bookPublish", bookPublish).toString();
    }

}
