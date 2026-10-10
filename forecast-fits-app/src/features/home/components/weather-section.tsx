import { ForecastVM, WeatherVM } from "@/utils/types";
import { View } from "react-native";
import CurrentWeather from "./current-weather";
import ForecastWeather from "./forecast";


interface WeatherProps {
    weather: WeatherVM,
    forecast: ForecastVM[] 
} 

const WeatherSection = ({ weather, forecast }: WeatherProps) => {
    
    return (
        <View className="w-full">
            <CurrentWeather {...weather}/>
            <ForecastWeather forecast={forecast}/>  
        </View>
    )
};

export default WeatherSection;