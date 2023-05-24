/*
   Copyright 2023 WeAreFrank!
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
       http://www.apache.org/licenses/LICENSE-2.0
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package nl.wearefrank.openapifrankadapter.xml.sender;

import io.swagger.v3.oas.models.PathItem;

import java.util.Map;

public class SenderClass {
    private final String senderName;

    public SenderClass(Map.Entry<String, PathItem> path) {
        this.senderName = path.getKey().substring(1);  // receiverName: remove the first slash
    }

    public String getSenderName() {
        return "Sender-" + this.senderName;
    }
}