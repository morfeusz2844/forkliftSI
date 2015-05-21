package genetic;

/**
 * Created by Patryk on 2015-05-19.
 */
public class Algorithm {

    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    //Evolve population in one generation
    public static Population evolvePopulation(Population pop){
        Population newPopulation = new Population(pop.populationSize());

        int elitismOffset =0;
        if(elitism){
            newPopulation.saveWorldMapGA(0,pop.getFittest());
            elitismOffset=1;
        }
        for(int i=elitismOffset;i<newPopulation.populationSize();i++){
            WarehouseMapGA parent1 = tournamentSelection(pop);
            WarehouseMapGA parent2 = tournamentSelection(pop);

            WarehouseMapGA child = crossover(parent1,parent2);

            newPopulation.saveWorldMapGA(i,child);
        }

        for(int i=elitismOffset;i<newPopulation.populationSize();i++){
            mutate(newPopulation.getWarehouseMapGA(i));
        }

        return newPopulation;
    }
    public static WarehouseMapGA crossover(WarehouseMapGA parent1,WarehouseMapGA parent2){
        WarehouseMapGA child = new WarehouseMapGA(parent1.getTruckPositionX(),parent1.getTruckPositionY());
        for (int i=0;i<(parent1.getPlaceMap().size()/2)+1;i++){
            child.addPlace(parent1.getPlaceMap().get(i));
        }
        for (int i=(parent2.getPlaceMap().size()/2)+1;i<parent2.getPlaceMap().size();i++){
            child.addPlace(parent2.getPlaceMap().get(i));
        }
        return child;
    }

    private static void mutate(WarehouseMapGA _WarehouseMapGA){

    }
    private static WarehouseMapGA tournamentSelection(Population pop){
        Population tournament = new Population(tournamentSize);

        for (int i=0;i<tournamentSize;i++){
            int randomId = (int)(Math.random()*pop.populationSize());
            tournament.saveWorldMapGA(i,pop.getWarehouseMapGA(randomId));
        }

        WarehouseMapGA fittest = tournament.getFittest();
        return fittest;
    }
}
