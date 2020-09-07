package ru.otus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MessageTest {

    private static final String F1 = "1";
    private static final String F2 = "2";
    private static final String F3 = "3";
    private static final String F4 = "4";
    private static final String F5 = "5";
    private static final String F6 = "6";
    private static final String F7 = "7";
    private static final String F8 = "8";
    private static final String F9 = "9";
    private static final String F10 = "10";
    private static final String F11 = "11";
    private static final String F12 = "12";
    private static final String F13 = "13";

    @Test
    @DisplayName("Тестируем правильность создания сообщений")
    void builderShouldCreatCorrectMessage() {
        Message.Builder builder = new Message.Builder();
        Message message = builder
                .field1(F1)
                .field2(F2)
                .field3(F3)
                .field4(F4)
                .field5(F5)
                .field6(F6)
                .field7(F7)
                .field8(F8)
                .field9(F9)
                .field10(F10)
                .field11(F11)
                .field12(F12)
                .field13(F13)
                .build();

        assertThat(message.getField1()).isEqualTo(F1);
        assertThat(message.getField2()).isEqualTo(F2);
        assertThat(message.getField3()).isEqualTo(F3);
        assertThat(message.getField4()).isEqualTo(F4);
        assertThat(message.getField5()).isEqualTo(F5);
        assertThat(message.getField6()).isEqualTo(F6);
        assertThat(message.getField7()).isEqualTo(F7);
        assertThat(message.getField8()).isEqualTo(F8);
        assertThat(message.getField9()).isEqualTo(F9);
        assertThat(message.getField10()).isEqualTo(F10);
        assertThat(message.getField11()).isEqualTo(F11);
        assertThat(message.getField12()).isEqualTo(F12);
        assertThat(message.getField13()).isEqualTo(F13);
    }

    @Test
    @DisplayName("Тестируем правильность создания сообщений")
    void messagesShouldBeEquals() {
        Message.Builder builder = new Message.Builder();
        builder
                .field1(F1)
                .field2(F2)
                .field3(F3)
                .field4(F4)
                .field5(F5)
                .field6(F6)
                .field7(F7)
                .field8(F8)
                .field9(F9)
                .field10(F10)
                .field11(F11)
                .field12(F12)
                .field13(F13);

        Message m1 = builder.build();
        Message m2 = builder.build();

        assertThat(m1).isEqualTo(m2);
    }

}