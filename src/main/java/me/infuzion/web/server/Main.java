/*
 * Copyright 2020 Srikavin Ramkumar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.infuzion.web.server;

import me.infuzion.web.server.event.def.PageRequestEvent;
import me.infuzion.web.server.event.reflect.EventHandler;
import me.infuzion.web.server.event.reflect.Route;
import me.infuzion.web.server.event.reflect.param.Response;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    static {
        String path = Server.class
                .getClassLoader()
                .getResource("logging.properties")
                .getFile();
        System.setProperty("java.util.logging.config.file", path);
    }

    public static void main(String[] args) throws IOException {
        int port = 9001;
        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        }
        Server server = new Server(new InetSocketAddress("0.0.0.0", port));
        server.getEventManager().registerListener(new EventListener() {
            @EventHandler
            @Route("*")
            @Response
            public String a(PageRequestEvent event) {
                return "TEST";
            }
        });
        server.start();
    }
}
