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
 	Date: 2016-4-24 I restored what I had in github. Not sure if all outlets are working correctly.
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
    section("Use this Simulated Switch for the Fan") {
    	input "switch1", "capability.switch", multiple: false, required:false
    }
    section("Use this Simulated Switch for the Orvibo") {
    	input "switch2", "capability.switch", multiple: false, required:false
    }
}

def installed()
{   
	updated()  
    
}

def updated()
{
	unsubscribe()
	subscribe(master, "contact", contactParser) 
    log.debug "Subscribed to ${master}"
    if(switch1) {
    	log.debug "Subscribed to ${switch1}"
    	subscribe(switch1, "switch.on", switchOn)
        subscribe(switch1, "switch.off", switchOff)
    }
    if(switch2) {
    	state.delay = 5000
    	log.debug "Subscribed to ${switch2}"
    	subscribe(switch2, "switchPsu.on", switchOrviboOn)
        subscribe(switch2, "switchPsu.off", switchOrviboOff)
        subscribe(switch2, "switchPsu.refresh", switchOrviboRefresh)
        subscribe(master, "switchOrvibo1.on", masterSlaveOn)
        subscribe(master, "switchOrvibo1.off", masterSlaveOff)
        state.updatedLast = now()
        
        
    }    
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

def switchOn(evt) {
	log.debug "Switch On"
    master.onA()

}

def switchOff(evt) {
	log.debug "Switch Off"
    master.offA()
}
def switchOrviboOn(evt) {
	log.debug "Switch On"
    /*
    state.now = now()
    state.diff = state.now - state.updatedLast
    log.debug "now: ${state.now} last: ${state.updatedLast} diff = ${state.diff}"
    if( now() - state.updatedLast  < state.delay) {
    	log.debug "Switch ON ignored because its feedback from arduino state change"
    	return
    }
    master.orviboOn1()
    state.updatedLast = now()
    */
    log.debug "EVT: name: ${evt.name} value: ${evt.value} descriptionText: ${evt.descriptionText}"
    if (evt.descriptionText == "Arduino"){
    	log.debug "DT is Arduino"
    } else {
    	log.debug "DT is not Arduino"
    	master.orviboOn1()
    }
    

}

def switchOrviboOff(evt) {
	log.debug "Switch Off"
    /*
    state.now = now()
    state.diff = state.now - state.updatedLast
    log.debug "now: ${state.now} last: ${state.updatedLast} diff = ${state.diff}"
        if( now() - state.updatedLast < state.delay) {
        
    	log.debug "Switch Off ignored because its feedback from arduino state change. Now: $now() and ${state.updatedLast} "
    	
        return
    }
    master.orviboOff1()
    state.updatedLast = now()
    */
    log.debug "EVT: name: ${evt.name} value: ${evt.value} descriptionText: ${evt.descriptionText}"
    if (evt.descriptionText == "Arduino"){
    	log.debug "DT is Arduino"
    } else {
    	log.debug "DT is not Arduino"
    	master.orviboOff1()
    }
}
def switchOrviboRefresh(evt) {
	log.debug "Refresh Orvibo"

    master.orviboRefresh1()

}
def masterSlaveOn(evt) {
	log.debug "Arduino turning on switch device"
    switch2.arduinoOn()
    /*
    state.now = now()
    state.diff = state.now - state.updatedLast
    log.debug "now: ${state.now} last: ${state.updatedLast} diff = ${state.diff}"   
    if( now() - state.updatedLast  < state.delay) {
    return
    }
    state.updatedLast = now()
    switch2.on()
    */
    
}
def masterSlaveOff(evt) {
	/*
    state.now = now()
    state.diff = state.now - state.updatedLast
    log.debug "now: ${state.now} last: ${state.updatedLast} diff = ${state.diff}"
	if( now() - state.updatedLast < state.delay) {
    
    return
    }
    
	log.debug "Arduino turning off switch device"
    state.updatedLast = now()
    switch2.off()
    */
    
    switch2.arduinoOff()
}