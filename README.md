#########################################
##       Crypto Market Service         ##
#########################################
https://coinmarketcap.com/api/documentation/v1/#operation/getV1CryptocurrencyListingsLatest

The API provides us the method v1/cryptocurrency/listings/latest to obtain a list of the latest market data.
It was used with additional query parameters to get only the first 30 coins and converted to our desired currency.
Query parameters:
start=1
limit=30
convert=USD or ARS (with a paid license, you could get simultaneous conversion to both currencies)

The data returned in this method is

{
    "status": {
        "timestamp": "2023-05-22T00:56:30.286Z",
        "error_code": 0,
        "error_message": null,
        "elapsed": 40,
        "credit_count": 1,
        "notice": null,
        "total_count": 9754
    },
    "data": [
        {
            "id": 1,
            "name": "Bitcoin",
            "symbol": "BTC",
            "slug": "bitcoin",
            "num_market_pairs": 10220,
            "date_added": "2010-07-13T00:00:00.000Z",
            "tags": [
                "mineable",
                "pow",
                "sha-256",
                "store-of-value",
                "multicoin-capital-portfolio",
                "paradigm-portfolio",
                "bitcoin-ecosystem"
            ],
            "max_supply": 21000000,
            "circulating_supply": 19380075,
            "total_supply": 19380075,
            "infinite_supply": false,
            "platform": null,
            "cmc_rank": 1,
            "self_reported_circulating_supply": null,
            "self_reported_market_cap": null,
            "tvl_ratio": null,
            "last_updated": "2023-05-22T00:55:00.000Z",
            "quote": {
                "USD": {
                    "price": 26668.156024922555,
                    "volume_24h": 8952864236.900589,
                    "volume_change_24h": 24.7659,
                    "percent_change_1h": -0.35081751,
                    "percent_change_24h": -2.02929032,
                    "percent_change_7d": -0.37207535,
                    "percent_change_30d": -2.05767135,
                    "percent_change_60d": -2.51004759,
                    "percent_change_90d": 7.19642235,
                    "market_cap": 516830863874.701,
                    "market_cap_dominance": 46.4102,
                    "fully_diluted_market_cap": 560031276523.37,
                    "tvl": null,
                    "last_updated": "2023-05-22T00:55:00.000Z"
                }
            }
        },
...
        ]
}

The image of each cryptocurrency is obtained from another API.
https://coinicons-api.vercel.app/api/icon/{coinSymbolLowercased}


#########################################
##        GitHub API Service           ##
#########################################
In order to display the Profile screen in the App, the Github API was used, using a Harcoded username. (This username should be provided in some sort of login Form)
It was only used the method to get a user's profile info:

https://api.github.com/users/{username}

This API doesn't need any Key or Authentication method of any kind.
The response with the key fields used in this App is:

{
    "login": "lcmendozaf",
    "id": 134106510,
    "avatar_url": "https://avatars.githubusercontent.com/u/134106510?v=4",
    "html_url": "https://github.com/lcmendozaf",
    "name": "Luis Mendoza",
    "location": null,
    "bio": null
}

The other fields in the response were not used in this App.

