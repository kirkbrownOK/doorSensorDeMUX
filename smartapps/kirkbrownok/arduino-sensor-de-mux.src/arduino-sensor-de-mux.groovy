/**
 *  Copyright 2015 Kirk Brown
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *  doorSensorDeMux
 *
 *  Author: Kirk Brown
 *
 *  Date: 2015-10-1
 */
definition(
	name: "Arduino Sensor De-Mux",
	namespace: "kirkbrownOK",
	author: "Kirk Brown",
	description: "Takes sensor events from arduino and applies them to individual virtual sensors for ease of use in other smartapps.",
	category: "Convenience",
	iconUrl: "https://s3.amazonaws.com/smartapp-icons/Meta/light_outlet.png",
	iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Meta/light_outlet@2x.png"
)

preferences {
	section("When this sensor has events: (Arduino or other MUX-ed sensor input)") {
		input "master", "capability.contactSensor", title: "Which arduino?"
	}
	section("Event 1 controls this virtual sensor:") {
		input "sensor1", "capability.contactSensor", multiple: false, required: false
	}
	section("Event 2 controls this virtual sensor:") {
		input "sensor2", "capability.contactSensor", multiple: false, required: false
	}
	section("Event 3 controls this virtual sensor") {
		input "sensor3", "capability.contactSensor", multiple: false, required: false
	}
	section("Event 4 controls this virtual sensor") {
		input "sensor4", "capability.contactSensor", multiple: false, required: false
	}
    	section("Event 5 controls this virtual sensor:") {
		input "sensor5", "capability.contactSensor", multiple: false, required: false
	}
	section("Event 6 controls this virtual sensor:") {
		input "sensor6", "capability.contactSensor", multiple: false, required: false
	}
	section("Event 7 controls this virtual sensor") {
		input "sensor7", "capability.contactSensor", multiple: false, required: false
	}
	section("Event 8 controls this virtual sensor") {
		input "sensor8", "capability.contactSensor", multiple: false, required: false
	}
}

def installed()
{   
	subscribe(master, "contact", contactParser)
}

def updated()
{
	unsubscribe()
	subscribe(master, "contact", contactParser)   
}

def logHandler(evt) {
	log.debug evt.value
}

def contactParser(evt) {
	def pair = evt.value.split(":")
    def calledSensor = pair[0].trim()
    def cmd = pair[1].trim()
    //log.debug "EVT value: ${evt.value} Name: ${pair[0].trim()}, value: ${pair[1].trim()}"
    
    if (calledSensor == 'Sensor1' && sensor1) {
    	log.debug "Sensor1: calling $cmd"
    	if(cmd == 'open') {
        	sensor1.open()
        } else if (cmd == 'close') {
        	sensor1.close()
        }        
    }  else if (calledSensor == 'Sensor2' && sensor2) {
    	log.debug "Sensor2: calling $cmd"
    	if(cmd == 'open') {
        	sensor2.open()
        } else if (cmd == 'close') {
        	sensor2.close()
        }        
    } else if (calledSensor == 'Sensor3' && sensor3) {
    	log.debug "Sensor3: calling $cmd"
    	if(cmd == 'open') {
        	sensor3.open()
        } else if (cmd == 'close') {
        	sensor3.close()
        }        
    } else if (calledSensor == 'Sensor4' && sensor4) {
    	log.debug "Sensor4: calling $cmd"
    	if(cmd == 'open') {
        	sensor4.open()
        } else if (cmd == 'close') {
        	sensor4.close()
        }        
    } else if (calledSensor == 'Sensor5' && sensor5) {
    	log.debug "Sensor5: calling $cmd"
    	if(cmd == 'open') {
        	sensor5.open()
        } else if (cmd == 'close') {
        	sensor5.close()
        }        
    } else if (calledSensor == 'Sensor6' && sensor5) {
    	if(cmd == 'open') {
        	sensor6.open()
        } else if (cmd == 'close') {
        	sensor6.close()
        }        
    } else if (calledSensor == 'Sensor7' && sensor7) {
    	log.debug "Sensor7: calling $cmd"
    	if(cmd == 'open') {
        	sensor7.open()
        } else if (cmd == 'close') {
        	sensor7.close()
        }        
    } else if (calledSensor == 'Sensor8' && sensor8) {
    	log.debug "Sensor8: calling $cmd"
    	if(cmd == 'open') {
        	sensor8.open()
        } else if (cmd == 'close') {
        	sensor8.close()
        }        
    } else {
    	log.info "Couldn't match value: ${evt.value}"
    }
	//log.debug onSwitches()
	//onSwitches()?.on()
    
}


private onSwitches() {
	if(switches && onSwitches) { switches + onSwitches }
	else if(switches) { switches }
	else { onSwitches }
}

private offSwitches() {
	if(switches && offSwitches) { switches + offSwitches }
	else if(switches) { switches }
	else { offSwitches }
}