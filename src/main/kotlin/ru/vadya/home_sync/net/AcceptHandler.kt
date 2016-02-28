package ru.vadya.home_sync.net

import java.net.Socket
import java.nio.charset.Charset

/**
 * Created by vadya on 27.02.16.
 */


class AcceptHandler(socket: Socket) : Runnable {
    private var socket: Socket = socket
    private var counter: Int = 0
    override fun run() {
        // из сокета клиента берём поток входящих данных
        val inputStream = socket.getInputStream()
        // и оттуда же - поток данных от сервера к клиенту
        val os = socket.getOutputStream()

        // буффер данных в 64 килобайта
        var buf = ByteArray(64 * 1024)
        // читаем 64кб от клиента, результат - кол-во реально принятых данных
        var r = inputStream.read(buf)

        // создаём строку, содержащую полученную от клиента информацию
        var data = String(buf, 0, r);

        // добавляем данные об адресе сокета:
        data = "Количество подключений: " + counter + "\nПолучено сообщение: " + data;

        // выводим данные:
        os.write(data.toByteArray(Charset.defaultCharset()));

        // завершаем соединение
        socket.close();
    }
}