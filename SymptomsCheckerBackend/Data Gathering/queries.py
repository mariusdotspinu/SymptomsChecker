DBPEDIA_query = """

select DISTINCT ?Disease ?Symptoms ?Source WHERE
{

 ?Source rdfs:label ?Disease.
 ?Source rdfs:comment ?Symptoms.
 ?Source a ?type.

VALUES (?type) {(yago:Disease114070360)}
FILTER (lang(?Symptoms ) = 'en')
FILTER (lang(?Disease ) = 'en')
}

GROUP BY ?Disease ?Symptoms ?Source ?type
ORDER BY ?Disease
"""

WIKIDATA_query = """
SELECT ?disease ?diseaseLabel (GROUP_CONCAT(?symptomsLabel; SEPARATOR = ", ") AS ?Symptoms) WHERE {
  ?disease wdt:P31 wd:Q12136.
  SERVICE wikibase:label { bd:serviceParam wikibase:language "[AUTO_LANGUAGE],en". }
  OPTIONAL
  {
    ?disease wdt:P780 ?symptoms.
    ?symptoms rdfs:label ?symptomsLabel
  }
  FILTER(LANGMATCHES(LANG(?symptomsLabel), 'en'))
}

GROUP BY ?disease ?diseaseLabel
ORDER BY ?diseaseLabel
"""