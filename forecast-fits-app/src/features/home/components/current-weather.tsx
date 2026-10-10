import { View, Text } from "react-native";
import { WeatherVM } from "@/utils/types";
import { weatherTheme } from "@/constants/colors/weather";

const CurrentWeather = ({ condition, temperature, location, windSpeed, relativeHumidity, cloudCover }: WeatherVM) => {

    const colors = weatherTheme[condition ?? 'null'] || weatherTheme['null'];

    return (
        <View 
            className="rounded-3xl p-6 my-2 shadow-lg shadow-black/40 w-full"
            style={{ backgroundColor: colors.background }}
        >
            <View className="flex flex-row justify-between items-center mb-6">
                <Text className="text-2xl font-semibold tracking-wide" style={{ color: colors.text }}>
                    {location}
                </Text>
                <Text className="text-lg font-medium" style={{ color: colors.text }}>
                    {condition.toUpperCase()}
                </Text>
            </View>
            <View className="flex flex-row justify-center items-center mb-8">
                <Text className="text-7xl font-bold" style={{ color: colors.text }}>
                    {temperature}°C
                </Text>
            </View>
            <View 
                className="flex flex-row justify-evenly items-center rounded-2xl p-4"
                style={{ backgroundColor: colors.box }}
            >
                <View className="flex flex-col items-center">
                    <Text className="text-xs uppercase tracking-wider mb-1" style={{ color: colors.text }}>Wind</Text>
                    <Text className="text-base font-semibold" style={{ color: colors.text }}>{windSpeed} km/h</Text>
                </View>
                <View className="w-[1px] h-full mx-2" style={{ backgroundColor: colors.text, opacity: 0.3 }} />
                <View className="flex flex-col items-center">
                    <Text className="text-xs uppercase tracking-wider mb-1" style={{ color: colors.text }}>Humidity</Text>
                    <Text className="text-base font-semibold" style={{ color: colors.text }}>{relativeHumidity}%</Text>
                </View>
                <View className="w-[1px] h-full mx-2" style={{ backgroundColor: colors.text, opacity: 0.3 }} />
                <View className="flex flex-col items-center">
                    <Text className="text-xs uppercase tracking-wider mb-1" style={{ color: colors.text }}>Clouds</Text>
                    <Text className="text-base font-semibold" style={{ color: colors.text }}>{cloudCover}%</Text>
                </View>
            </View>
        </View>
    );
}

export default CurrentWeather;