export type WeatherVM = {
    location: string,
    cloudCover: number,
    condition: string,
    relativeHumidity: number,
    temperature: number,
    windSpeed: number
}

export type ForecastVM = {
    time: string,
    cloudCover: number,
    condition: string,
    temperature: number,
    windSpeed: number
}

export type RecommendationVM = {
    baseLayer: string,
    midLayer: string,
    outerLayer: string,
    bottom: string,
    footwear: string,
    headwear: string,
    accessoires: string
}