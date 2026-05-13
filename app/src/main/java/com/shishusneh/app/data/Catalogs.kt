package com.shishusneh.app.data

object MilestoneCatalog {
    data class MilestoneDef(val id: String, val title: String, val ageWeeks: Int, val emoji: String)

    val MILESTONES = listOf(
        MilestoneDef("smile", "Baby smiles back", 6, "😊"),
        MilestoneDef("head", "Holds head up steadily", 12, "🙆"),
        MilestoneDef("sound", "Responds to sound", 14, "👂"),
        MilestoneDef("roll", "Rolls over", 18, "🌀"),
        MilestoneDef("sit", "Sits with support", 24, "🪑"),
        MilestoneDef("babble", "Babbles ‘ma-ma’ ‘ba-ba’", 30, "🗣️"),
        MilestoneDef("crawl", "Crawls", 36, "🐾"),
        MilestoneDef("stand", "Stands holding furniture", 40, "🧍"),
        MilestoneDef("wave", "Waves bye-bye", 44, "👋"),
        MilestoneDef("walk", "First steps", 52, "👣")
    )
}

object FeedingTips {
    data class Tip(val title: String, val body: String, val emoji: String)

    val TIPS = listOf(
        Tip("Only breast milk for 6 months", "Exclusive breastfeeding protects baby from infections. No water needed.", "🍼"),
        Tip("Feed on demand", "Healthy newborns feed 8–12 times in 24 hours. Trust your baby’s cues.", "👶"),
        Tip("Mother’s plate matters", "Eat dal, green leafy vegetables, eggs and fruits. You need 500 extra calories daily.", "🥬"),
        Tip("Iron-rich foods (after 6 months)", "Mashed spinach, ragi, dal water and egg yolk help prevent anemia.", "🥚"),
        Tip("Drink water often", "A breastfeeding mother needs 3 litres of water every day.", "💧"),
        Tip("Skin-to-skin time", "Hold baby on your bare chest daily — it boosts milk and bonding.", "🤱"),
        Tip("Burp after every feed", "Hold baby upright on your shoulder and pat gently for 2–3 minutes.", "🫧"),
        Tip("Mashed banana is gold", "After 6 months, ripe mashed banana is a perfect first food.", "🍌"),
        Tip("Avoid honey before 1 year", "Honey can cause infant botulism. Wait until baby is 12 months old.", "🚫"),
        Tip("Sun for Vitamin D", "10–15 minutes of morning sun (before 9am) keeps baby’s bones strong.", "🌅"),
        Tip("Wash hands before feeding", "Clean hands prevent diarrhea — the leading cause of infant illness.", "🧼"),
        Tip("Continue till 2 years", "Even after starting solids, breast milk remains baby’s best friend.", "💗")
    )

    fun tipForToday(): Tip {
        val day = (System.currentTimeMillis() / (24L * 60 * 60 * 1000)).toInt()
        return TIPS[((day % TIPS.size) + TIPS.size) % TIPS.size]
    }
}
