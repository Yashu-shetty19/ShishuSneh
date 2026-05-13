package com.shishusneh.app.data

import java.util.concurrent.TimeUnit

/** Vaccine catalog (India IAP-inspired, simplified) and helpers. */
object VaccineCatalog {

    data class VaccineDef(
        val id: String,
        val name: String,
        val ageLabel: String,
        val offsetDays: Int,
        val prevents: String
    )

    val SCHEDULE: List<VaccineDef> = listOf(
        VaccineDef("bcg", "BCG", "At birth", 0, "Tuberculosis (TB)"),
        VaccineDef("opv0", "OPV 0 (Polio drops)", "At birth", 0, "Polio"),
        VaccineDef("hepb0", "Hepatitis B (Birth dose)", "At birth", 0, "Hepatitis B"),
        VaccineDef("dpt1", "DPT 1", "6 weeks", 42, "Diphtheria, Pertussis, Tetanus"),
        VaccineDef("opv1", "OPV 1", "6 weeks", 42, "Polio"),
        VaccineDef("rota1", "Rotavirus 1", "6 weeks", 42, "Severe diarrhea"),
        VaccineDef("pcv1", "PCV 1", "6 weeks", 42, "Pneumonia"),
        VaccineDef("dpt2", "DPT 2", "10 weeks", 70, "Diphtheria, Pertussis, Tetanus"),
        VaccineDef("opv2", "OPV 2", "10 weeks", 70, "Polio"),
        VaccineDef("rota2", "Rotavirus 2", "10 weeks", 70, "Severe diarrhea"),
        VaccineDef("dpt3", "DPT 3", "14 weeks", 98, "Diphtheria, Pertussis, Tetanus"),
        VaccineDef("opv3", "OPV 3", "14 weeks", 98, "Polio"),
        VaccineDef("pcv2", "PCV 2", "14 weeks", 98, "Pneumonia"),
        VaccineDef("mr1", "Measles–Rubella 1", "9 months", 270, "Measles, Rubella"),
        VaccineDef("vita", "Vitamin A (1st dose)", "9 months", 270, "Vitamin A deficiency"),
        VaccineDef("jec", "JE Vaccine", "9 months", 270, "Japanese Encephalitis"),
        VaccineDef("mr2", "Measles–Rubella 2", "12 months", 360, "Measles, Rubella")
    )

    fun dueDateMillis(dobMillis: Long, def: VaccineDef): Long =
        dobMillis + TimeUnit.DAYS.toMillis(def.offsetDays.toLong())
}
