import { View, Image, Pressable, Text } from "react-native";
import { useRouter } from "expo-router";
import { RecommendationVM } from "@/utils/types";

const RecommendationSection = ({ baseLayer, midLayer, outerLayer, bottom, footwear, headwear, accessoires }: RecommendationVM) => {

    const router = useRouter();

    const handleSeeFit = () => {
        router.push({
            pathname: '/(recommendation)/recommendation',
            params: {
                baseLayer: baseLayer,
                midLayer: midLayer,
                outerLayer: outerLayer,
                bottom: bottom,
                footwear: footwear,
                headwear: headwear,
                accessoires: accessoires
            }
        });
    };

    return (
        <View className="flex-1 w-full my-2 bg-slate-100 rounded-3xl shadow-lg shadow-black/40">
            <Image 
                source={require('@/assets/images/test-tshirt.png')}
                className="w-full h-full absolute"
                resizeMode="contain"
            />
            <View className="absolute bottom-8 w-full items-center">
                <Pressable 
                    onPress={handleSeeFit}
                    className="bg-white/95 px-10 py-4 rounded-full shadow-lg"
                >
                    <Text className="text-slate-900 font-bold tracking-widest uppercase text-sm">
                        See Fit
                    </Text>
                </Pressable>
            </View>
        </View>
    )
};

export default RecommendationSection;