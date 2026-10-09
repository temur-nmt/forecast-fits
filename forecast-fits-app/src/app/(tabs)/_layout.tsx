import { Tabs } from "expo-router"
import { House, WashingMachine, Cloud, Shirt, Settings } from 'lucide-react-native'

/**
 * Using https://lucide.dev/icons/ for icons
 */

const TabsLayout = () => {
    return (
        <Tabs screenOptions={{headerShown: false}}>
            <Tabs.Screen name="index" options={{
                title: "Home",
                tabBarIcon: ({color, size}) => (
                    <House size={size} color={color}/>
                )    
            }}>
            </Tabs.Screen>
            <Tabs.Screen name="weather" options={{
                title: "Weather",
                tabBarIcon: ({color, size}) => (
                    <Cloud size={size} color={color}/>
                )    
            }}>
            </Tabs.Screen>
            <Tabs.Screen name="closet" options={{
                title: "Closet",
                tabBarIcon: ({color, size}) => (
                    <Shirt size={size} color={color}/>
                )    
            }}></Tabs.Screen>
            <Tabs.Screen name="basket" options={{
                title: "Basket",
                tabBarIcon: ({color, size}) => (
                    <WashingMachine size={size} color={color}/>
                )    
            }}>
            </Tabs.Screen>
            <Tabs.Screen name="settings" options={{
                title: "Settings",
                tabBarIcon: ({color, size}) => (
                    <Settings size={size} color={color}/>
                )    
            }}>
            </Tabs.Screen>
        </Tabs>
    )
};

export default TabsLayout;