import { ForecastVM } from "@/utils/types";
import { ScrollView, Text, View } from "react-native";
import { weatherTheme } from "@/constants/colors/weather";

const ForecastWeather = ({ forecast }: { forecast: ForecastVM[] }) => {
    
    const currentHour = new Date().getHours();
    const upcomingForecast = forecast.filter(item => {
        const localDate = new Date(item.time);
        return (localDate.getHours() >= currentHour) || localDate.getHours() == 0;
    });

    return (
        <View className="bg-slate-800 rounded-3xl my-2 shadow-lg shadow-black/40 w-full py-6">
            <ScrollView 
                horizontal={true} 
                showsHorizontalScrollIndicator={false}
                bounces={true} 
                contentContainerStyle={{ paddingHorizontal: 20, gap: 16 }} 
            >
                {upcomingForecast.map((item, index) => {
                    const localDate = new Date(item.time);
                    const timeString = `${localDate.getHours().toString().padStart(2, '0')}:00`;
                    const colors = weatherTheme[item.condition ?? 'null'] || weatherTheme['null'];

                    return (
                        <View 
                            key={index}
                            className="flex flex-col justify-between items-center px-4 py-5 rounded-3xl w-28"
                            style={{ backgroundColor: colors.background }}
                        >
                            <Text 
                                className="text-sm font-medium mb-4 opacity-90" 
                                style={{ color: colors.text }}
                            >
                                {timeString}
                            </Text>

                            <Text 
                                className="text-3xl font-bold mb-1" 
                                style={{ color: colors.text }}
                            >
                                {item.temperature}°
                            </Text>

                            <Text 
                                className="text-[10px] uppercase font-bold tracking-widest mb-4 opacity-80" 
                                style={{ color: colors.text }}
                            >
                                {item.condition}
                            </Text>

                            <View 
                                className="px-2 py-1.5 rounded-xl w-full items-center"
                                style={{ backgroundColor: colors.box }}
                            >
                                <Text 
                                    className="text-xs font-semibold" 
                                    style={{ color: colors.text }}
                                >
                                    {item.windSpeed} km/h
                                </Text>
                            </View>
                        </View>
                    );
                })}
            </ScrollView>
        </View>
    );
};

export default ForecastWeather;